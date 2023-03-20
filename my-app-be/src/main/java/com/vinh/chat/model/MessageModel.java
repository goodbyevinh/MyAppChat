package com.vinh.chat.model;


public class MessageModel {

    private AccountModel sender;
    private String content;
    private String date;
    private TypeMessage typeMessage;

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
}
