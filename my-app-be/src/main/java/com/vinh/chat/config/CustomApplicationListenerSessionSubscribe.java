package com.vinh.chat.config;

import com.vinh.chat.constant.ApiConstant;
import com.vinh.chat.dto.FriendDTO;
import com.vinh.chat.entity.AccountEntity;
import com.vinh.chat.model.Status;
import com.vinh.chat.model.mongodb.ImageEntity;
import com.vinh.chat.repository.AccountRepository;
import com.vinh.chat.repository.mongodb.ImageRepository;
import com.vinh.chat.service.GroupService;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomApplicationListenerSessionSubscribe implements ApplicationListener<SessionSubscribeEvent> {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    GroupService groupService;
    @Override
    public void onApplicationEvent(SessionSubscribeEvent event) {
        String simpDestination = event.getMessage().getHeaders().get("simpDestination").toString();

        Map<String, Object> map = new HashMap<>();
        FriendDTO friendDTO = null;
        switch (simpDestination) {
            case ApiConstant.END_POINT_CHAT_ROOM_PUBLIC : {
                map.put("status", Status.FRIEND_GROUP);
                friendDTO = groupService.updateFriendGroup(event.getUser().getName(), true);
                map.put("friend" , friendDTO);
                break;
            }
        }
        simpMessagingTemplate.convertAndSend(ApiConstant.END_POINT_CHAT_ROOM_PUBLIC, map);
    }


}
