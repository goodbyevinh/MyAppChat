package com.vinh.chat.service.imp;

import com.vinh.chat.dto.FriendDTO;
import com.vinh.chat.dto.GroupDTO;
import com.vinh.chat.entity.AccountEntity;
import com.vinh.chat.entity.GroupEntity;
import com.vinh.chat.model.mongodb.ImageEntity;
import com.vinh.chat.repository.AccountRepository;
import com.vinh.chat.repository.mongodb.ImageRepository;
import com.vinh.chat.service.GroupService;
import com.vinh.chat.utils.ComponentUtils;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class GroupServiceImp implements GroupService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    ComponentUtils componentUtils;
    @Override
    public GroupDTO insertGroup(String name) {
        AccountEntity account = accountRepository.findByEmail(componentUtils.getEmail());
        GroupEntity group = new GroupEntity();
        group.setName(name);
        group.setAccount(account);
        account.getGroups().add(group);
        try {
            GroupDTO groupDTO = new GroupDTO();
            accountRepository.save(account);
            //groupDTO.setId(account.getGroups().stream().fi);
            groupDTO.setName(group.getName());
            return groupDTO;
        }catch ( Exception e) {
            return null;
        }

    }

    @Override
    public boolean deleteGroup(int id) {
        AccountEntity account = accountRepository.findByEmail(componentUtils.getEmail());
        account.getGroups().removeIf(group -> group.getId() == id);
        try {
            accountRepository.save(account);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<GroupDTO> getGroups() {
        AccountEntity account = accountRepository.findByEmail(componentUtils.getEmail());
        List<GroupDTO> list = new ArrayList<>();
        account.getGroups().forEach(group -> {
            GroupDTO groupDTO = new GroupDTO();
            groupDTO.setId(group.getId());
            groupDTO.setName(group.getName());
            list.add(groupDTO);
        });
        return list;
    }

    @Override
    public boolean inviteFriend(int groupId, int accountId) {

        return false;
    }

    @Override
    public FriendDTO updateFriendGroup(String email, boolean isOnline) {
        FriendDTO friendDTO = new FriendDTO();

        AccountEntity account = accountRepository.findByEmail(email);
        friendDTO.setId(account.getId());
        friendDTO.setName(account.getFullName());
        friendDTO.setEmail(account.getEmail());
        friendDTO.setOnline(isOnline);
        ImageEntity image = imageRepository.findByEmail(account.getEmail());
        friendDTO.setOauth2(image.isOauth2());
        if (image.isOauth2()) {
            friendDTO.setAvatar(((String) image.getImage()));
        } else {
            friendDTO.setAvatar(Base64.getEncoder().encodeToString(((Binary) image.getImage()).getData()));
        }
        return friendDTO;
    }
}
