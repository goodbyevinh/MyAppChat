package com.vinh.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OAuthController {

    @GetMapping("/login")
    public String getRequest(){
        return "welcome";
    }
}
