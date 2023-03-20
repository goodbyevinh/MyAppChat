package com.vinh.chat.service;

import com.vinh.chat.dto.FriendDTO;
import com.vinh.chat.entity.AccountEntity;

import java.util.List;

public interface FriendService {
    List<FriendDTO> getFriends() ;

    FriendDTO acceptFriend(int id) ;
    FriendDTO updateStatus(String email, boolean isOnline);

    boolean deleteFriend(int id);

}
