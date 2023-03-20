package com.vinh.chat.controller;

import com.vinh.chat.dto.MessageDTO;
import com.vinh.chat.dto.MessageJoinLeaveDTO;
import com.vinh.chat.model.MessageModel;
import com.vinh.chat.model.UserOnlineModel;
import com.vinh.chat.payload.request.MessagePrivateRequest;
import com.vinh.chat.payload.request.MessageRequest;
import com.vinh.chat.service.MessageService;
import com.vinh.chat.utils.ComponentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin
public class ChatController {
    @Autowired
    MessageService messageService;

    @Autowired
    ComponentUtils componentUtils;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/public-message") // client gửi msg
    @SendTo("/chatroom/public") // kênh nhận msg
    public Object publicMessage(@Payload MessageRequest messageRequest){
        MessageDTO message = null;

        switch (messageRequest.getStatus()) {
            case JOIN: {
                message = new MessageDTO();
                message.setStatus(messageRequest.getStatus());
                message.setTypeChat(messageRequest.getTypeChat());

                break;
            }
            case MESSAGE: {
                message = messageService.insertPublicMessage(messageRequest);
                break;
            }
            case LEAVE: {
                break;
            }
        }

        return message;
    }

    @MessageMapping("/private-message")
    public Object privateMessage(@Payload MessagePrivateRequest messageRequest){
        MessageDTO message = null;

        switch (messageRequest.getStatus()) {
            case JOIN: {
                message = new MessageDTO();
                message.setStatus(messageRequest.getStatus());
                message.setTypeChat(messageRequest.getTypeChat());
                break;
            }
            case MESSAGE: {
                message = messageService.insertPrivateMessage(messageRequest);
                break;
            }
            case LEAVE: {
                break;
            }
        }
        simpMessagingTemplate.convertAndSendToUser(messageRequest.getReceiver().getEmail(),"/private",message);
        return null;

    }

    @MessageMapping("/group-message")
    public MessageModel groupMessage(@Payload MessageModel message){

        return message;
    }


}
