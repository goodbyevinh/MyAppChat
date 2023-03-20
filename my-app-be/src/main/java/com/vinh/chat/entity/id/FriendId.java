package com.vinh.chat.entity.id;

import javax.persistence.Column;
import java.io.Serializable;


public class FriendId implements Serializable {

    @Column(name = "account_me_id")
    private int accountMeId;
    @Column(name = "account_other_id")
    private int accountOtherId;


    public FriendId() {
    }

    public FriendId(int accountMeId, int accountOtherId) {
        this.accountMeId = accountMeId;
        this.accountOtherId = accountOtherId;
    }

    public int getAccountMeId() {
        return accountMeId;
    }

    public void setAccountMeId(int accountMeId) {
        this.accountMeId = accountMeId;
    }

    public int getAccountOtherId() {
        return accountOtherId;
    }

    public void setAccountOtherId(int accountOtherId) {
        this.accountOtherId = accountOtherId;
    }
}
