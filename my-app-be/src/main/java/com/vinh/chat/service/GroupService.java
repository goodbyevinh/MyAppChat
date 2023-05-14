package com.vinh.chat.service;

import com.vinh.chat.dto.FriendDTO;
import com.vinh.chat.dto.GroupDTO;

import java.util.List;

public interface GroupService {
    GroupDTO insertGroup(String name);
    boolean deleteGroup(int id);
    List<GroupDTO> getGroups();
    boolean inviteFriend(int groupId, int accountId);

    FriendDTO updateFriendGroup(String email, boolean isOnline);
}
