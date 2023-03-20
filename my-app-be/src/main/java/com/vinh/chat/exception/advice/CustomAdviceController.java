package com.vinh.chat.exception.advice;

import com.vinh.chat.exception.CustomException;
import com.vinh.chat.payload.response.DataResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;


@ControllerAdvice
public class CustomAdviceController {

    @ExceptionHandler(CustomException.class )
    public ResponseEntity<?> handleTokenErrorException(Exception e) {

        DataResponse dataResponse = new DataResponse();
        dataResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        dataResponse.setDesc(e.getMessage());

        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
}
