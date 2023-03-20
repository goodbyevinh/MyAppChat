package com.vinh.chat.entity;


import javax.persistence.*;
import java.util.Set;


@Entity(name = "account")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "fullname")
    private String fullName;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "is_oauth2")
    private boolean isOauth2;

    @OneToMany(mappedBy = "accountMe" , cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FriendEntity> friendsMe;
    // Gần giống nhau
    @OneToMany(mappedBy = "accountOther", cascade = CascadeType.ALL)
    private Set<FriendEntity> friendsOther;

    //invite
    @OneToMany(mappedBy = "accountMe" , cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<InviteFriendEntity> inviteFriendsMe;
    // Gần giống nhau
    @OneToMany(mappedBy = "accountFriend", cascade = CascadeType.ALL)
    private Set<InviteFriendEntity> inviteFriendsOther;


    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GroupEntity> groups;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private Set<GroupAccountEntity> groupAccounts;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")

    private RoleEntity role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }



    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Set<FriendEntity> getFriendsMe() {
        return friendsMe;
    }

    public void setFriendsMe(Set<FriendEntity> friendsMe) {
        this.friendsMe = friendsMe;
    }

    public Set<FriendEntity> getFriendsOther() {
        return friendsOther;
    }

    public void setFriendsOther(Set<FriendEntity> friendsOther) {
        this.friendsOther = friendsOther;
    }

    public Set<GroupEntity> getGroups() {
        return groups;
    }

    public void setGroups(Set<GroupEntity> groups) {
        this.groups = groups;
    }

    public Set<GroupAccountEntity> getGroupAccounts() {
        return groupAccounts;
    }

    public void setGroupAccounts(Set<GroupAccountEntity> groupAccounts) {
        this.groupAccounts = groupAccounts;
    }

    public boolean isOauth2() {
        return isOauth2;
    }

    public void setOauth2(boolean oauth2) {
        isOauth2 = oauth2;
    }

    public Set<InviteFriendEntity> getInviteFriendsMe() {
        return inviteFriendsMe;
    }

    public void setInviteFriendsMe(Set<InviteFriendEntity> inviteFriendsMe) {
        this.inviteFriendsMe = inviteFriendsMe;
    }

    public Set<InviteFriendEntity> getInviteFriendsOther() {
        return inviteFriendsOther;
    }

    public void setInviteFriendsOther(Set<InviteFriendEntity> inviteFriendsOther) {
        this.inviteFriendsOther = inviteFriendsOther;
    }
}
