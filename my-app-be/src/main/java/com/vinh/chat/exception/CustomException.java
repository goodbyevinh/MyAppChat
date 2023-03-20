package com.vinh.chat.exception;

import org.springframework.stereotype.Component;

@Component
public class CustomException extends RuntimeException{
    public CustomException() {

        super();
    }
    public CustomException(String message) {
        super(message);
    }
}
