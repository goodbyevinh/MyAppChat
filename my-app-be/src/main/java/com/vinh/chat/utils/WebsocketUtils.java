package com.vinh.chat.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class WebsocketUtils {
    @Autowired
    SimpUserRegistry simpUserRegistry;

    public Set<SimpUser> getUsers() {
        return simpUserRegistry.getUsers();
    }

    public boolean checkUserSubcribe(String name){

        SimpUser simpUser = simpUserRegistry.getUser(name);
        return simpUser != null;
    }
}
