package com.vinh.chat.service;

import com.vinh.chat.dto.ImageDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    String addImage(String title, String email, MultipartFile file) throws IOException;
    String addImage(String title, MultipartFile file) throws IOException;

    ImageDTO getImage(String id);
    ImageDTO getAvatar(String email);
}
