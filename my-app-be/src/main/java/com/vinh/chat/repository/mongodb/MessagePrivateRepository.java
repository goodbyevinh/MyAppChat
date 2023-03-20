package com.vinh.chat.repository.mongodb;

import com.vinh.chat.model.mongodb.MessagePrivateEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessagePrivateRepository extends MongoRepository<MessagePrivateEntity, Integer> {
    List<MessagePrivateEntity> findBySenderEmailInOrReceiverEmailIn(List<String> sender, List<String> receiver);
}
