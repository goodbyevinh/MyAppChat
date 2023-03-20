package com.vinh.chat.controller;

import com.vinh.chat.dto.MessageDTO;
import com.vinh.chat.dto.MessageDTOs;
import com.vinh.chat.payload.response.DataResponse;
import com.vinh.chat.service.MessageService;
import com.vinh.chat.utils.ComponentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
@CrossOrigin
public class MessageController {
    @Autowired
    MessageService messageService;
    @Autowired
    ComponentUtils componentUtils;


    @GetMapping("/public")
    ResponseEntity<?> getPublicMessage() {
        MessageDTOs messageDTOs = messageService.getPublicMessage();
        DataResponse dataResponse = new DataResponse();
        dataResponse.setSuccess(true);
        dataResponse.setData(messageDTOs);
        dataResponse.setDesc("getPublicMessage");
        dataResponse.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

    @GetMapping("/private/{email}")
    ResponseEntity<?> getPrivateMessage(@PathVariable(name = "email") String email) {
        MessageDTOs messageDTOs =  messageService.getPrivateMessage(email);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setDesc("get private message");
        dataResponse.setSuccess(true);
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setData(messageDTOs);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
}
