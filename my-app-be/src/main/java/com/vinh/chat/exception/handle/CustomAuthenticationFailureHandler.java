package com.vinh.chat.exception.handle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinh.chat.payload.response.DataResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        DataResponse dataResponse = new DataResponse();
        dataResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        dataResponse.setDesc(exception.getMessage());
        dataResponse.setDesc(HttpStatus.INTERNAL_SERVER_ERROR.name());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.getOutputStream()
                .println(objectMapper.writeValueAsString(dataResponse));
    }
}
