package com.vinh.chat.config;


import com.vinh.chat.entity.AccountEntity;
import com.vinh.chat.model.Status;
import com.vinh.chat.repository.AccountRepository;
import com.vinh.chat.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CustomApplicationListenerSessionUnconnected implements ApplicationListener<SessionDisconnectEvent> {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    FriendService friendService;

    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {

        Map<String, Object> map = new HashMap<>();
        map.put("status", Status.ONLINE);
        map.put("friend", friendService.updateStatus(event.getUser().getName(), false));
        List<AccountEntity> friendList = accountRepository.findListFriendOfMe(event.getUser().getName());
        friendList.forEach( friend -> {
            simpMessagingTemplate.convertAndSendToUser(friend.getEmail(), "/private", map);
        });
    }
}
