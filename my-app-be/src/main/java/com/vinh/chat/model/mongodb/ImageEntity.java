package com.vinh.chat.model.mongodb;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "images")
public class ImageEntity {
    @Id
    private String id;

    @Indexed(unique = true)
    private String email;
    private boolean isOauth2;

    private String title;

    private Object image;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isOauth2() {
        return isOauth2;
    }

    public void setOauth2(boolean oauth2) {
        isOauth2 = oauth2;
    }
}
