package com.vinh.chat.exception;

import javax.naming.AuthenticationException;

public class CustomAuthenticationException extends AuthenticationException  {
    public CustomAuthenticationException( ) {

    }

    public CustomAuthenticationException(String msg){
        super(msg);
    }
}
