package com.vinh.chat.service.imp;

import com.vinh.chat.dto.ImageDTO;
import com.vinh.chat.entity.AccountEntity;
import com.vinh.chat.model.mongodb.ImageEntity;
import com.vinh.chat.repository.AuthRepository;
import com.vinh.chat.repository.mongodb.ImageRepository;
import com.vinh.chat.service.ImageService;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;


@Service
public class ImageServiceImp implements ImageService {

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private AuthRepository authRepository;

    @Override
    public String addImage(String title, String email, MultipartFile file) throws IOException {
        if (authRepository.findByEmail(email)  != null) {
            return null;
        }
        ImageEntity image = new ImageEntity();
        image.setTitle(title);
        image.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        image.setEmail(email);
        image = imageRepository.save(image);
        return image.getId();
    }

    @Override
    public String addImage(String title, MultipartFile file) throws IOException {

        ImageEntity image = new ImageEntity();
        image.setTitle(title);
        image.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));

        image = imageRepository.save(image);
        return image.getId();
    }



    @Override
    public ImageDTO getImage(String id) {
        ImageDTO imageDTO = new ImageDTO();
        ImageEntity image = imageRepository.findById(id).get();
        imageDTO.setTitle(image.getTitle());
        imageDTO.setImage(Base64.getEncoder().encodeToString(((Binary) image.getImage()).getData()));
        return imageDTO;
    }

    @Override
    public ImageDTO getAvatar(String email) {
        ImageEntity image = imageRepository.findByEmail(email);
        ImageDTO imageDTO = new ImageDTO();
        if (image.isOauth2()) {
            imageDTO.setOauth2(image.isOauth2());
            imageDTO.setImage((String) image.getImage());
        } else {
            imageDTO.setOauth2(image.isOauth2());
            imageDTO.setImage(Base64.getEncoder().encodeToString(((Binary) image.getImage()).getData()));
        }
        return imageDTO;
    }
}
