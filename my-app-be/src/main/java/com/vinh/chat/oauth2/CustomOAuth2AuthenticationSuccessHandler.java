package com.vinh.chat.oauth2;

import com.vinh.chat.constant.NumberConstant;
import com.vinh.chat.jwt.JwtTokenHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


@Component
public class CustomOAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler  {
    @Autowired
    JwtTokenHelper jwtTokenHelper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        String token = jwtTokenHelper.generateToken(customOAuth2User.getEmail(),"token", "ROLE_USER" ,NumberConstant.expiredDate);
        String refreshToken = jwtTokenHelper.generateToken(customOAuth2User.getEmail(),"token", "ROLE_USER" , NumberConstant.refreshExpiredDate);
        String url = processUrl(request, token, refreshToken);
        response.sendRedirect(url);
    }


    private String processUrl(HttpServletRequest request, String token, String refreshToken) {
        Cookie[] cookies = request.getCookies();
        String redirectUri = "";
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("redirect_uri")) {
                    redirectUri = cookie.getValue();
                    break;
                }
            }
        }


       return redirectUri + "?token=" + token + "&refresh=" +refreshToken;

    }


}
