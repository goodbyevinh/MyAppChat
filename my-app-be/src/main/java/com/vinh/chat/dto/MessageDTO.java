package com.vinh.chat.dto;

import com.vinh.chat.model.AccountModel;
import com.vinh.chat.model.Status;
import com.vinh.chat.model.TypeChat;
import com.vinh.chat.model.TypeMessage;

public class MessageDTO {

    private AccountModel sender;
    private String content;
    private String date;
    private Status status;

    private TypeMessage typeMessage;

    private TypeChat typeChat;

    public TypeChat getTypeChat() {
        return typeChat;
    }

    public void setTypeChat(TypeChat typeChat) {
        this.typeChat = typeChat;
    }

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


}
