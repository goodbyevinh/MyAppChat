package com.vinh.chat.jwt;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private Gson gson = new Gson();
    @Autowired
    JwtTokenHelper jwtTokenHelper;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = null;

        if (request.getParameter("token") != null) {
            token = request.getParameter("token");
        } else {
            token = getTokenFromHeader(request);
        }

        if (token != null) {
            if (jwtTokenHelper.validateToken(token)) {
                String json = jwtTokenHelper.decodeToken(token);
                Map<String, Object> map = gson.fromJson(json, Map.class);

                if (StringUtils.hasText(map.get("type").toString()) && !map.get("type").toString().equals("refresh")) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(map.get("username"),"", AuthorityUtils.createAuthorityList(((String) map.get("role"))));
                    SecurityContext securityContext = SecurityContextHolder.getContext();
                    securityContext.setAuthentication(authenticationToken);
                }
            }else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromHeader(HttpServletRequest request) {
        String strToken = request.getHeader("Authorization");
        if (StringUtils.hasText(strToken) && strToken.startsWith("Bearer")) {
            String finalToken = strToken.substring(7);
            return finalToken;
        } else  {
            return null;
        }
    }
}
