package com.vinh.chat.config;

import com.vinh.chat.constant.ApiConstant;
import com.vinh.chat.dto.FriendDTO;
import com.vinh.chat.model.Status;
import com.vinh.chat.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomApplicationListenerSessionUnsubscribe implements ApplicationListener<SessionUnsubscribeEvent> {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    GroupService groupService;

    @Override
    public void onApplicationEvent(SessionUnsubscribeEvent event) {
        String simpDestination = event.getMessage().getHeaders().get("simpDestination").toString();
        System.out.println(simpDestination);
        Map<String, Object> map = new HashMap<>();
        FriendDTO friendDTO = null;
        switch (simpDestination) {
            case ApiConstant.END_POINT_CHAT_ROOM_PUBLIC : {
                map.put("status", Status.FRIEND_GROUP);
                friendDTO = groupService.updateFriendGroup(event.getUser().getName(), false);
                map.put("friend" , friendDTO);
                break;
            }
        }
        simpMessagingTemplate.convertAndSend(ApiConstant.END_POINT_CHAT_ROOM_PUBLIC, map);
    }
}
