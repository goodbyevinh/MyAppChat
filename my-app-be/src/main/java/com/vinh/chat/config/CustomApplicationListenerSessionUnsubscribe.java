package com.vinh.chat.config;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

@Component
public class CustomApplicationListenerSessionUnsubscribe implements ApplicationListener<SessionUnsubscribeEvent> {
    @Override
    public void onApplicationEvent(SessionUnsubscribeEvent event) {
        System.out.println("Unsubcribe" + event.getUser());
    }
}
