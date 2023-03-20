package com.vinh.chat.repository.mongodb;

import com.vinh.chat.model.mongodb.MessageEntity;
import com.vinh.chat.model.mongodb.MessagePublicEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessagePublicRepository extends MongoRepository<MessagePublicEntity, String> {

}
