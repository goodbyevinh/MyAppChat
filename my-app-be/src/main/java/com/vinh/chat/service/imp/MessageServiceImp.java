package com.vinh.chat.service.imp;


import com.vinh.chat.dto.FriendDTO;
import com.vinh.chat.dto.MessageDTO;
import com.vinh.chat.dto.MessageDTOs;
import com.vinh.chat.entity.AccountEntity;
import com.vinh.chat.model.AccountModel;
import com.vinh.chat.model.MessageModel;
import com.vinh.chat.model.TypeChat;

import com.vinh.chat.model.TypeMessage;
import com.vinh.chat.model.mongodb.ImageEntity;
import com.vinh.chat.model.mongodb.MessagePrivateEntity;
import com.vinh.chat.model.mongodb.MessagePublicEntity;
import com.vinh.chat.payload.request.MessagePrivateRequest;
import com.vinh.chat.payload.request.MessageRequest;
import com.vinh.chat.repository.AccountRepository;
import com.vinh.chat.repository.AuthRepository;
import com.vinh.chat.repository.mongodb.ImageRepository;
import com.vinh.chat.repository.mongodb.MessagePrivateRepository;
import com.vinh.chat.repository.mongodb.MessagePublicRepository;
import com.vinh.chat.service.MessageService;
import com.vinh.chat.utils.ComponentUtils;
import com.vinh.chat.utils.WebsocketUtils;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class MessageServiceImp implements MessageService {

    @Autowired
    MessagePublicRepository messagePublicRepository;
    @Autowired
    MessagePrivateRepository messagePrivateRepository;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    AuthRepository authRepository;
    @Autowired
    ComponentUtils componentUtils;
    @Autowired
    WebsocketUtils websocketUtils;


    @Override
    public MessageDTO insertPublicMessage(MessageRequest message) {
        try {
            MessagePublicEntity messagePublicEntity = new MessagePublicEntity();
            messagePublicEntity.setContent(message.getContent());
            messagePublicEntity.setDate(message.getDate());
            messagePublicEntity.setEmail(message.getSender().getEmail());
            messagePublicEntity.setTypeMessage(message.getTypeMessage());
            messagePublicEntity.setName(message.getSender().getName());

            messagePublicRepository.insert(messagePublicEntity);

            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setContent(message.getContent());
            messageDTO.setDate(message.getDate());
            messageDTO.setTypeMessage(message.getTypeMessage());
            messageDTO.setStatus(message.getStatus());

            AccountModel accountModel = new AccountModel();
            accountModel.setName(message.getSender().getName());
            accountModel.setEmail(message.getSender().getEmail());

            messageDTO.setSender(accountModel);
            return messageDTO;

        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public MessageDTOs getPublicMessage() {
        MessageDTOs messageDTOs = new MessageDTOs();
        messageDTOs.setTypeChat(TypeChat.CHAT_PUBLIC);
        List<MessageModel> list = new ArrayList<>();
        List<MessagePublicEntity> messagePublicEntityList = messagePublicRepository.findAll();
        if (messagePublicEntityList.isEmpty()) {

        } else {
            messagePublicEntityList.forEach(message -> {
                MessageModel messageModel = new MessageModel();
                if (message.getTypeMessage().equals(TypeMessage.IMAGE)) {
                    ImageEntity img = imageRepository.findById(message.getContent()).get();
                    messageModel.setContent(Base64.getEncoder().encodeToString(((Binary) img.getImage()).getData()));
                } else if (message.getTypeMessage().equals(TypeMessage.VIDEO)) {

                } else {
                    messageModel.setContent(message.getContent());
                }
                messageModel.setDate(message.getDate());
                messageModel.setTypeMessage(message.getTypeMessage());
                AccountModel accountModel = new AccountModel();
                accountModel.setName(message.getName());
                accountModel.setEmail(message.getEmail());

                ImageEntity image = imageRepository.findByEmail(message.getEmail());
                accountModel.setOauth2(image.isOauth2());

                if (image.isOauth2()) {
                    accountModel.setAvatar((String) image.getImage());
                } else {
                    accountModel.setAvatar(Base64.getEncoder().encodeToString(((Binary) image.getImage()).getData()));
                }

                messageModel.setSender(accountModel);
                list.add(messageModel);
            });
        }
        messageDTOs.setMessages(list);

        Set<SimpUser> simpUsers = websocketUtils.getUsers();
        List<FriendDTO> friends = new ArrayList<>();
        Set<String> usersWithPublic = simpUsers.stream().filter( simpUser -> {
            boolean isSub = simpUser.getSessions()
                    .stream().anyMatch(session -> session.getSubscriptions()
                            .stream().anyMatch(simpSubscription -> simpSubscription.getDestination().equals("/chatroom/public")));
            return isSub;
        }).map(simpUser -> simpUser.getName()).collect(Collectors.toSet());

        usersWithPublic.forEach(email -> {
            AccountEntity account = authRepository.findByEmail(email);
            FriendDTO friendDTO = new FriendDTO();
            friendDTO.setEmail(account.getEmail());
            friendDTO.setName(account.getFullName());
            ImageEntity image = imageRepository.findByEmail(email);
            friendDTO.setOauth2(image.isOauth2());

            if (image.isOauth2()) {
                friendDTO.setAvatar(((String) image.getImage()));
            } else {
                friendDTO.setAvatar(Base64.getEncoder().encodeToString(((Binary) image.getImage()).getData()));
            }
            friends.add(friendDTO);
        });
        messageDTOs.setFriends(friends);
        return messageDTOs;
    }

    @Override
    public MessageDTO insertPrivateMessage(MessagePrivateRequest message) {
        MessagePrivateEntity messagePrivateEntity = new MessagePrivateEntity();
        messagePrivateEntity.setSenderName(message.getSender().getName());
        messagePrivateEntity.setSenderEmail(message.getSender().getEmail());
        messagePrivateEntity.setReceiverName(message.getReceiver().getName());
        messagePrivateEntity.setReceiverEmail(message.getReceiver().getEmail());
        messagePrivateEntity.setDate(message.getDate());
        messagePrivateEntity.setTypeMessage(message.getTypeMessage());
        messagePrivateEntity.setContent(message.getContent());
        try {
            messagePrivateRepository.save(messagePrivateEntity);

            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setContent(message.getContent());
            messageDTO.setDate(message.getDate());
            messageDTO.setTypeMessage(message.getTypeMessage());
            messageDTO.setStatus(message.getStatus());

            AccountModel accountModel = new AccountModel();
            accountModel.setName(message.getSender().getName());
            accountModel.setEmail(message.getSender().getEmail());

            messageDTO.setSender(accountModel);
            return messageDTO;

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public MessageDTOs getPrivateMessage(String emailOther) {
        MessageDTOs messageDTOs = new MessageDTOs();
        messageDTOs.setTypeChat(TypeChat.CHAT_PRIVATE);
        String emailMe = componentUtils.getEmail();
        List<String> listEmail = List.of(emailMe, emailOther);
        List<MessagePrivateEntity> messagePrivateEntities = messagePrivateRepository
                .findBySenderEmailInOrReceiverEmailIn(listEmail, listEmail);
        List<MessageModel> list = new ArrayList<>();
        messagePrivateEntities.forEach(message -> {
            MessageModel messageModel = new MessageModel();
            messageModel.setDate(message.getDate());
            if (message.getTypeMessage().equals(TypeMessage.IMAGE)) {
                ImageEntity img = imageRepository.findById(message.getContent()).get();
                messageModel.setContent(Base64.getEncoder().encodeToString(((Binary) img.getImage()).getData()));
            } else if (message.getTypeMessage().equals(TypeMessage.VIDEO)) {

            } else {
                messageModel.setContent(message.getContent());
            }
            messageModel.setTypeMessage(message.getTypeMessage());
            AccountModel accountModel = new AccountModel();
            accountModel.setName(message.getSenderName());
            accountModel.setEmail(message.getSenderEmail());
            ImageEntity image = imageRepository.findByEmail(message.getSenderEmail());
            accountModel.setOauth2(image.isOauth2());
            if (image.isOauth2()) {
                accountModel.setAvatar((String) image.getImage());
            } else {
                accountModel.setAvatar(Base64.getEncoder().encodeToString(((Binary) image.getImage()).getData()));
            }
            messageModel.setSender(accountModel);
            list.add(messageModel);
        });
        messageDTOs.setMessages(list);
        return messageDTOs;
    }






}
