package com.vinh.chat.payload.request;

import com.vinh.chat.model.AccountModel;
import com.vinh.chat.model.Status;
import com.vinh.chat.model.TypeChat;
import com.vinh.chat.model.TypeMessage;

public class MessageRequest {
    private AccountModel sender;
    private String content;
    private String date;
    private TypeMessage typeMessage;
    private Status status;
    private TypeChat typeChat;

    public AccountModel getSender() {
        return sender;
    }

    public void setSender(AccountModel sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public TypeMessage getTypeMessage() {
        return typeMessage;
    }

    public void setTypeMessage(TypeMessage typeMessage) {
        this.typeMessage = typeMessage;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public TypeChat getTypeChat() {
        return typeChat;
    }

    public void setTypeChat(TypeChat typeChat) {
        this.typeChat = typeChat;
    }
}
