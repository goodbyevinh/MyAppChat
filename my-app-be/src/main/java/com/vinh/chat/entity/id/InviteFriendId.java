package com.vinh.chat.entity.id;

import javax.persistence.Column;
import java.io.Serializable;


public class InviteFriendId implements Serializable {

    @Column(name = "account_me_id")
    private int accountMeId;
    @Column(name = "account_friend_id")
    private int accountFriendId;

    public InviteFriendId() {
    }

    public InviteFriendId(int accountMeId, int accountFriendId) {
        this.accountMeId = accountMeId;
        this.accountFriendId = accountFriendId;
    }

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
}
