package com.vinh.chat.entity;


import com.vinh.chat.entity.id.InviteFriendId;

import javax.persistence.*;

@Entity(name = "invite_friend")
@IdClass(InviteFriendId.class)
public class InviteFriendEntity {
    @Id
    private int accountMeId;
    @Id
    private int accountFriendId;

    @ManyToOne
    @JoinColumn(name = "account_me_id", insertable = false, updatable = false)
    private AccountEntity accountMe;

    @ManyToOne
    @JoinColumn(name = "account_friend_id", insertable = false, updatable = false)
    private AccountEntity accountFriend;

    public int getAccountMeId() {
        return accountMeId;
    }

    public void setAccountMeId(int accountMeId) {
        this.accountMeId = accountMeId;
    }

    public int getAccountFriendId() {
        return accountFriendId;
    }

    public void setAccountFriendId(int accountFriendId) {
        this.accountFriendId = accountFriendId;
    }

    public AccountEntity getAccountMe() {
        return accountMe;
    }

    public void setAccountMe(AccountEntity accountMe) {
        this.accountMe = accountMe;
    }

    public AccountEntity getAccountFriend() {
        return accountFriend;
    }

    public void setAccountFriend(AccountEntity accountFriend) {
        this.accountFriend = accountFriend;
    }
}
