package com.vinh.chat.entity.id;

import javax.persistence.Column;
import java.io.Serializable;

public class GroupAccountId implements Serializable {

    @Column(name = "account_id")
    private int accountId;
    @Column(name = "group_id")
    private int groupId;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
