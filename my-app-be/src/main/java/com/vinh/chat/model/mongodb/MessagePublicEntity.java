package com.vinh.chat.model.mongodb;

import com.vinh.chat.model.TypeMessage;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "message_public")
public class MessagePublicEntity {
    @Id
    private String id;
    private String email;
    private String content;
    private String date;
    private String name;




    private TypeMessage typeMessage;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TypeMessage getTypeMessage() {
        return typeMessage;
    }

    public void setTypeMessage(TypeMessage typeMessage) {
        this.typeMessage = typeMessage;
    }


}
