package com.vinh.chat.exception.handle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinh.chat.payload.response.DataResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        DataResponse dataResponse = new DataResponse();
        dataResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        dataResponse.setDesc(accessDeniedException.getMessage());
        dataResponse.setDesc(HttpStatus.INTERNAL_SERVER_ERROR.name());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.getOutputStream()
                .println(objectMapper.writeValueAsString(dataResponse));

    }
}
