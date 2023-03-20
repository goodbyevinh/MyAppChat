package com.vinh.chat.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "c_group")
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    @OneToMany(mappedBy = "group")
    private Set<GroupAccountEntity> groupAccounts;

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

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public Set<GroupAccountEntity> getGroupAccounts() {
        return groupAccounts;
    }

    public void setGroupAccounts(Set<GroupAccountEntity> groupAccounts) {
        this.groupAccounts = groupAccounts;
    }
}
