package com.vinh.chat.controller;


import com.vinh.chat.dto.DataToken;
import com.vinh.chat.exception.CustomException;
import com.vinh.chat.payload.request.LoginRequest;
import com.vinh.chat.payload.request.SignupRequest;
import com.vinh.chat.payload.response.DataResponse;
import com.vinh.chat.repository.mongodb.MessagePublicRepository;
import com.vinh.chat.service.AuthService;
import com.vinh.chat.service.ImageService;
import com.vinh.chat.service.UserService;
import com.vinh.chat.utils.ComponentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;
    @Autowired
    UserService userService;
    @Autowired
    ImageService imageService;
    @Autowired
    ComponentUtils componentUtils;
    @Autowired
    MessagePublicRepository messageRepository;

    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        DataResponse dataResponse = new DataResponse();
        DataToken dataToken = authService.authentication(loginRequest.getEmail(), loginRequest.getPassword());
        dataResponse.setData(dataToken);
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc("");
        dataResponse.setSuccess(true);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    ResponseEntity<?> refreshToken(@RequestParam(name = "token") String token) {
        DataResponse dataResponse = new DataResponse();
        DataToken dataToken = authService.refreshToken(token);
        dataResponse.setData(dataToken != null ? dataToken : "" );
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc(dataToken != null ? "success refresh token": "fail refresh token");
        dataResponse.setSuccess(dataToken != null);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
    @PostMapping("/signup")
    ResponseEntity<?> signup(@RequestParam(name = "signup") String signup,
                             @RequestParam(name = "title") String title,
                             @RequestParam(name = "image") MultipartFile image) throws IOException {

        DataResponse dataResponse = new DataResponse();
        SignupRequest signupRequest = componentUtils.getGson().fromJson(signup, SignupRequest.class);
        String avatar = imageService.addImage(title,signupRequest.getEmail(), image);
        DataToken dataToken = null;
        if (avatar != null ){
            dataToken = authService.insertUser(signupRequest, avatar);
        }
        dataResponse.setData(dataToken);
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc("");
        dataResponse.setSuccess(dataToken != null);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

    @GetMapping("/check-user/{email}")
    ResponseEntity<?> checkUser(@PathVariable(name = "email") String email) {
        DataResponse dataResponse = new DataResponse();
        dataResponse.setData("check user");
        dataResponse.setData("");
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setSuccess(userService.checkLogin(email) != null);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }



    @PostMapping("/test")
    public String test() {
        return "hello";
    }


}
