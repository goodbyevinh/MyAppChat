package com.vinh.chat.controller;


import com.vinh.chat.utils.WebsocketUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/test")
public class TestController {
    @Autowired
    WebsocketUtils websocketUtils;

    @GetMapping("/count")
    public Object getCount() {
        Set<SimpUser> set = websocketUtils.getUsers();

        return set;
    }
    @GetMapping("/check/{name}")
    public Object checkUser(@PathVariable(name = "name") String name) {
        return websocketUtils.checkUserSubcribe(name);
    }

}
