package com.vinh.chat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

@Component
public class CustomApplicationListenerSessionSubscribe implements ApplicationListener<SessionSubscribeEvent> {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    @Override
    public void onApplicationEvent(SessionSubscribeEvent event) {
        event.getMessage().getHeaders().get("simpDestination");
        System.out.println(event.getUser().getName());
    }




}
