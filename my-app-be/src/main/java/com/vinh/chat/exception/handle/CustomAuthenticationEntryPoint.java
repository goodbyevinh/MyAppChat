package com.vinh.chat.exception.handle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinh.chat.payload.response.DataResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        DataResponse dataResponse = new DataResponse();
        dataResponse.setStatus(HttpStatus.FORBIDDEN.value());
        dataResponse.setDesc(authException.getMessage());
        dataResponse.setData(HttpStatus.FORBIDDEN.name());
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.getOutputStream()
                .println(objectMapper.writeValueAsString(dataResponse));
    }
}
