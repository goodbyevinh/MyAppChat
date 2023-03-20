package com.vinh.chat.controller;

import com.vinh.chat.dto.AccountDTO;
import com.vinh.chat.payload.response.DataResponse;
import com.vinh.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/account")
public class AccountController {

    @Autowired
    UserService userService;

    @GetMapping("/get")
    ResponseEntity<?> getUser() {
        DataResponse dataResponse = new DataResponse();
        AccountDTO accountDTO = userService.getAccount();
        dataResponse.setData("get user");
        dataResponse.setData(accountDTO);
        dataResponse.setStatus(HttpStatus.OK.value());

        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

}
