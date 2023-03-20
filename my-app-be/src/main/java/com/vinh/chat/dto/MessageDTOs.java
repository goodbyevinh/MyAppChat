package com.vinh.chat.dto;

import com.vinh.chat.model.MessageModel;
import com.vinh.chat.model.TypeChat;

import java.util.List;

public class MessageDTOs {
    List<MessageModel> messages;
    TypeChat typeChat;

    List<FriendDTO> friends;

    public List<MessageModel> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageModel> messages) {
        this.messages = messages;
    }

    public TypeChat getTypeChat() {
        return typeChat;
    }

    public void setTypeChat(TypeChat typeChat) {
        this.typeChat = typeChat;
    }

    public List<FriendDTO> getFriends() {
        return friends;
    }

    public void setFriends(List<FriendDTO> friends) {
        this.friends = friends;
    }
}
