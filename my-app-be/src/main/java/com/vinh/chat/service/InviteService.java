package com.vinh.chat.service;

import com.vinh.chat.dto.InviteFriendDTO;

import java.util.List;

public interface InviteService {

    List<InviteFriendDTO> getNotFriends();


    List<InviteFriendDTO> getInvitedFriends();
    boolean inviteFriend(int id);




}
