package com.vinh.chat.dto;

import com.vinh.chat.model.Status;
import com.vinh.chat.model.TypeChat;

public class MessageJoinLeaveDTO {
    private Status status;
    private TypeChat typeChat;

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
