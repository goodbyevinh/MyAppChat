package com.vinh.chat.controller;


import com.vinh.chat.dto.FriendDTO;
import com.vinh.chat.dto.InviteFriendDTO;
import com.vinh.chat.payload.response.DataResponse;
import com.vinh.chat.service.FriendService;
import com.vinh.chat.service.InviteService;
import com.vinh.chat.utils.ComponentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    FriendService friendService;
    @Autowired
    InviteService inviteService;

    @GetMapping("/accept/{id}")
    public ResponseEntity<?> acceptFriend(@PathVariable(name = "id") int id) {
        FriendDTO friendDTO = friendService.acceptFriend(id);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc("accept friend");
        dataResponse.setData(friendDTO);
        dataResponse.setSuccess(friendDTO != null);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

    @GetMapping("/get-friends")
    public ResponseEntity<?> getFriends() {
        List<FriendDTO> list = friendService.getFriends();
        DataResponse dataResponse = new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc("list friend");
        dataResponse.setData(list);
        dataResponse.setSuccess(true);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteFriend(@PathVariable(name = "id") int id) {

        boolean isSucess = friendService.deleteFriend(id);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc("delete friend");
        dataResponse.setData("");
        dataResponse.setSuccess(isSucess);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

    @GetMapping("/get-not-friends")
    public ResponseEntity<?> getNotFriend() {
        List<InviteFriendDTO> inviteFriendDTOS = inviteService.getNotFriends();
        DataResponse dataResponse = new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc("get not invite friend");
        dataResponse.setData(inviteFriendDTOS);
        dataResponse.setSuccess(true);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

    @GetMapping("/get-invited-friends")
    public ResponseEntity<?> getInvitedFriend() {
        List<InviteFriendDTO> inviteFriendDTOS = inviteService.getInvitedFriends();
        DataResponse dataResponse = new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc("get invited friend");
        dataResponse.setData(inviteFriendDTOS);
        dataResponse.setSuccess(true);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

    @GetMapping("/invite/{id}")
    public ResponseEntity<?> inviteFriend(@PathVariable(name = "id") int id) {

        boolean isSucess = inviteService.inviteFriend(id);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc("invite friend");
        dataResponse.setData("");
        dataResponse.setSuccess(isSucess);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

}
