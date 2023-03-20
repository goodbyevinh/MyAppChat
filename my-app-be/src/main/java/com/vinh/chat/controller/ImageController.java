package com.vinh.chat.controller;

import com.vinh.chat.dto.ImageDTO;
import com.vinh.chat.payload.response.DataResponse;
import com.vinh.chat.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/image")
@CrossOrigin
@RestController
public class ImageController {
    @Autowired
    ImageService imageService;

    @PostMapping("/add")
    public ResponseEntity<?> addImage(@RequestParam("title") String title, @RequestParam("image")MultipartFile image) throws IOException {

        DataResponse dataResponse = new DataResponse();
        String id = imageService.addImage(title, image);
        dataResponse.setData(id);
        dataResponse.setStatus(HttpStatus.OK.value());
        dataResponse.setDesc("Store Image");
        dataResponse.setSuccess(true);
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getPhoto(@PathVariable String id ) {
        ImageDTO image = imageService.getImage(id);
        DataResponse dataResponse = new DataResponse();
        dataResponse.setSuccess(true);
        dataResponse.setDesc("Get Image");
        dataResponse.setData(image);
        return  new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getAvatarByEmail (@PathVariable String email) {
        return  new ResponseEntity<>(imageService.getAvatar(email), HttpStatus.OK);
    }


}
