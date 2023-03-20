package com.vinh.chat.entity;

import com.vinh.chat.entity.id.FriendId;

import javax.persistence.*;

@Entity(name = "account_account")
@IdClass(FriendId.class)
public class FriendEntity {
    @Id
    private int accountMeId;
    @Id
    private int accountOtherId;

    @ManyToOne
    @JoinColumn(name = "account_me_id", insertable = false, updatable = false)
    private AccountEntity accountMe;

    @ManyToOne
    @JoinColumn(name = "account_other_id", insertable = false, updatable = false)
    private AccountEntity accountOther;



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

    public AccountEntity getAccountMe() {
        return accountMe;
    }

    public void setAccountMe(AccountEntity accountMe) {
        this.accountMe = accountMe;
    }

    public AccountEntity getAccountOther() {
        return accountOther;
    }

    public void setAccountOther(AccountEntity accountOther) {
        this.accountOther = accountOther;
    }

}
