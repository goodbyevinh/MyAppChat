package com.vinh.chat.service.imp;

import com.vinh.chat.dto.GroupDTO;
import com.vinh.chat.entity.AccountEntity;
import com.vinh.chat.entity.GroupEntity;
import com.vinh.chat.repository.AccountRepository;
import com.vinh.chat.service.GroupService;
import com.vinh.chat.utils.ComponentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupServiceImp implements GroupService {
    @Autowired
    AccountRepository accountRepository;
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
}
