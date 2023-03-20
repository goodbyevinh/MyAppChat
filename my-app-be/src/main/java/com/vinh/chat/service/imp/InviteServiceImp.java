package com.vinh.chat.service.imp;

import com.vinh.chat.dto.InviteFriendDTO;
import com.vinh.chat.entity.AccountEntity;
import com.vinh.chat.entity.InviteFriendEntity;
import com.vinh.chat.model.mongodb.ImageEntity;
import com.vinh.chat.repository.AccountRepository;
import com.vinh.chat.repository.mongodb.ImageRepository;
import com.vinh.chat.service.InviteService;
import com.vinh.chat.utils.ComponentUtils;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;


@Service
public class InviteServiceImp implements InviteService {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    ComponentUtils componentUtils;

    @Override
    public List<InviteFriendDTO> getNotFriends() {
        List<AccountEntity> listFriend = accountRepository.findListFriendOfMe(componentUtils.getEmail());
        List<String> friendEmails = listFriend.stream().map(friend -> friend.getEmail()).collect(Collectors.toList());
        friendEmails.add(componentUtils.getEmail());
        List<AccountEntity> listNotFriend = accountRepository.findAccountEntitiesByEmailNotIn(friendEmails);
        List<InviteFriendDTO> inviteFriendDTOS = new ArrayList<>();
        listNotFriend.forEach(account -> {
            InviteFriendDTO inviteFriendDTO = new InviteFriendDTO();
            inviteFriendDTO.setId(account.getId());
            inviteFriendDTO.setEmail(account.getEmail());
            inviteFriendDTO.setName(account.getFullName());
            inviteFriendDTO.setOauth2(account.isOauth2());
            ImageEntity image =  imageRepository.findByEmail(account.getEmail());

            if (image.isOauth2()) {
                inviteFriendDTO.setAvatar(((String) image.getImage()));
            } else {
                inviteFriendDTO.setAvatar(Base64.getEncoder().encodeToString(((Binary) image.getImage()).getData()));
            }

            boolean is1 = account.getInviteFriendsMe().stream().anyMatch(data -> data.getAccountFriend().getEmail().equals(componentUtils.getEmail()));
            boolean is2 = account.getInviteFriendsOther().stream().anyMatch(data -> data.getAccountMe().getEmail().equals(componentUtils.getEmail()));


            inviteFriendDTO.setInvite(is1 || is2 );
            inviteFriendDTOS.add(inviteFriendDTO);
        });

        return inviteFriendDTOS;
    }

    @Override
    public List<InviteFriendDTO> getInvitedFriends() {
        List<AccountEntity> accountEntities = accountRepository.findListInvitedOfMe(componentUtils.getEmail());
        List<InviteFriendDTO> inviteFriendDTOS = new ArrayList<>();
        accountEntities.forEach(account -> {
            InviteFriendDTO inviteFriendDTO = new InviteFriendDTO();
            inviteFriendDTO.setId(account.getId());
            inviteFriendDTO.setName(account.getFullName());
            inviteFriendDTO.setEmail(account.getEmail());
            inviteFriendDTO.setOauth2(account.isOauth2());
            ImageEntity image = imageRepository.findByEmail(account.getEmail());
            if (image.isOauth2()) {
                inviteFriendDTO.setAvatar(((String) image.getImage()));
            } else {
                inviteFriendDTO.setAvatar(Base64.getEncoder().encodeToString(((Binary) image.getImage()).getData()));
            }
            inviteFriendDTOS.add(inviteFriendDTO);
        });
        return inviteFriendDTOS;
    }

    @Override
    public boolean inviteFriend(int id) {
        AccountEntity invitedAccount  = accountRepository.findById(id).get();

        AccountEntity accountMe = accountRepository.findByEmail(componentUtils.getEmail());

        InviteFriendEntity inviteFriendEntity = new InviteFriendEntity();
        inviteFriendEntity.setAccountMeId(accountMe.getId());
        inviteFriendEntity.setAccountFriendId(invitedAccount.getId());
        accountMe.getInviteFriendsMe().add(inviteFriendEntity);

        try {
            accountRepository.save(accountMe);
            return true;
        } catch (Exception e){
            return false;
        }
    }


}
