package com.vinh.chat.dto;

public class FriendDTO {
    private int id;
    private String name;
    private String avatar;
    private boolean isOauth2;
    private boolean isOnline;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }
}
