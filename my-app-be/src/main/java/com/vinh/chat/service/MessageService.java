package com.vinh.chat.service;


import com.vinh.chat.dto.MessageDTO;
import com.vinh.chat.dto.MessageDTOs;
import com.vinh.chat.payload.request.MessagePrivateRequest;
import com.vinh.chat.payload.request.MessageRequest;

public interface MessageService {
    MessageDTO insertPublicMessage(MessageRequest message) ;
    MessageDTOs getPublicMessage();

    MessageDTO insertPrivateMessage(MessagePrivateRequest message);
    MessageDTOs getPrivateMessage(String emailOther);

}
