package com.vinh.chat.entity;

import com.vinh.chat.entity.id.FriendId;
import com.vinh.chat.entity.id.GroupAccountId;

import javax.persistence.*;

@Entity(name = "group_account")
@IdClass(GroupAccountId.class)
public class GroupAccountEntity {
    @Id
    private int groupId;
    @Id
    private int accountId;
    @ManyToOne
    @JoinColumn(name = "account_id" , insertable = false, updatable = false)
    private AccountEntity account;
    @ManyToOne
    @JoinColumn(name = "group_id" , insertable = false, updatable = false)
    private GroupEntity group;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }

}
