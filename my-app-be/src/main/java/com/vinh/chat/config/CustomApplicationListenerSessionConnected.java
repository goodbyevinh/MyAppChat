package com.vinh.chat.config;

import com.vinh.chat.dto.FriendDTO;
import com.vinh.chat.entity.AccountEntity;
import com.vinh.chat.model.Status;
import com.vinh.chat.model.mongodb.ImageEntity;
import com.vinh.chat.repository.AccountRepository;
import com.vinh.chat.repository.mongodb.ImageRepository;
import com.vinh.chat.service.FriendService;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.broker.SimpleBrokerMessageHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CustomApplicationListenerSessionConnected implements ApplicationListener<SessionConnectedEvent> {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    FriendService friendService;

    @Override
    public void onApplicationEvent(SessionConnectedEvent event) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", Status.ONLINE);
        map.put("friend", friendService.updateStatus(event.getUser().getName(), true));
        List<AccountEntity> friendList = accountRepository.findListFriendOfMe(event.getUser().getName());
        friendList.forEach( friend -> {
            simpMessagingTemplate.convertAndSendToUser(friend.getEmail(), "/private", map);
        });
    }


}
