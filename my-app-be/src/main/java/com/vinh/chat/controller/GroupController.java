package com.vinh.chat.controller;

import com.vinh.chat.dto.GroupDTO;
import com.vinh.chat.payload.response.DataResponse;
import com.vinh.chat.service.GroupService;
import com.vinh.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/group")
public class GroupController {

    @Autowired
    GroupService groupService;

    @GetMapping("/insert/{name}")
    public ResponseEntity<?> insertGroup(@PathVariable(name = "name") String name) {
        GroupDTO groupDTO = groupService.insertGroup(name);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setSuccess(groupDTO != null);
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setData(groupDTO);
        dataResponse.setDesc("insert group");
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable(name = "id") int id) {
        boolean isSuccess = groupService.deleteGroup(id);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setSuccess(false);
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setData("");
        dataResponse.setDesc("delete group");
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

    @GetMapping("/get-groups")
    public ResponseEntity<?> getGroups() {
        List<GroupDTO> list = groupService.getGroups();
        DataResponse dataResponse = new DataResponse();
        dataResponse.setSuccess(false);
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setData(list);
        dataResponse.setDesc("get groups");
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

    @GetMapping("/invite")
    public ResponseEntity<?> inviteFriend(@RequestParam(name = "groupId") int accountId,
                                          @RequestParam(name = "accountId") int groupId) {
        return new ResponseEntity<>("", HttpStatus.OK);
    }



}
