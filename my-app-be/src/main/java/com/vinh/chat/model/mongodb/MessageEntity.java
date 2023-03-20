package com.vinh.chat.model.mongodb;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;



public class MessageEntity {

    private String id;
    private String senderName;
    private String receiverName;
    private String message;
    private String date;


    public MessageEntity() {
    }

    public MessageEntity(String id, String senderName, String receiverName, String message, String date) {
        super();
        this.id = id;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.message = message;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
