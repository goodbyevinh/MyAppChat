package com.vinh.chat.repository.mongodb;

import com.vinh.chat.model.mongodb.ImageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public interface ImageRepository extends MongoRepository<ImageEntity, String> {


    @Query("{ 'email' : ?0 }")
    ImageEntity findByEmail(String email);



    Optional<ImageEntity> findImageEntityByEmail(String email);
}
