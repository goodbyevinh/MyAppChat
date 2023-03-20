package com.vinh.chat.service.imp;

import com.vinh.chat.dto.FriendDTO;
import com.vinh.chat.entity.AccountEntity;
import com.vinh.chat.entity.FriendEntity;
import com.vinh.chat.model.Status;
import com.vinh.chat.model.mongodb.ImageEntity;
import com.vinh.chat.repository.AccountRepository;
import com.vinh.chat.repository.mongodb.ImageRepository;
import com.vinh.chat.service.FriendService;
import com.vinh.chat.utils.ComponentUtils;
import com.vinh.chat.utils.WebsocketUtils;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FriendServiceImp implements FriendService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ComponentUtils componentUtils;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    WebsocketUtils websocketUtils;


    @Override
    public FriendDTO acceptFriend(int id) {
        AccountEntity accountMe = accountRepository.findByEmail(componentUtils.getEmail());
        AccountEntity accountOther = accountRepository.findById(id).get();

        accountOther.getInviteFriendsMe().removeIf(account -> account.getAccountFriendId() == accountMe.getId());
        accountMe.getInviteFriendsMe().removeIf(account -> account.getAccountFriendId() == id);

        FriendEntity friendMe = new FriendEntity();
        friendMe.setAccountMeId(accountMe.getId());
        friendMe.setAccountOtherId(accountOther.getId());
        accountMe.getFriendsMe().add(friendMe);

        FriendEntity friendOther = new FriendEntity();
        friendOther.setAccountMeId(accountOther.getId());
        friendOther.setAccountOtherId(accountMe.getId());
        accountOther.getFriendsMe().add(friendOther);


        try  {
            accountRepository.save(accountMe);
            accountRepository.save(accountOther);
            FriendDTO friendDTO = new FriendDTO();

            friendDTO.setId(accountOther.getId());
            friendDTO.setEmail(accountOther.getEmail());
            friendDTO.setName(accountOther.getFullName());

            ImageEntity image = imageRepository.findByEmail(accountOther.getEmail());

            if (image.isOauth2()) {
                friendDTO.setAvatar(((String) image.getImage()));
            } else {
                friendDTO.setAvatar(Base64.getEncoder().encodeToString(((Binary) image.getImage()).getData()));
            }
            return friendDTO;

        } catch (Exception e) {
            return null;
        }
    }


    @Override
    public List<FriendDTO> getFriends() {
        AccountEntity account = accountRepository.findByEmail(componentUtils.getEmail());
        List<FriendDTO> list = new ArrayList<>();
        Set<SimpUser> simpUsers = websocketUtils.getUsers();
        account.getFriendsMe().forEach(friendMe -> {
            FriendDTO friendDTO = new FriendDTO();
            friendDTO.setId(friendMe.getAccountOtherId());
            friendDTO.setName(friendMe.getAccountOther().getFullName());
            friendDTO.setEmail(friendMe.getAccountOther().getEmail());
            boolean isOnline = simpUsers.stream().anyMatch(simpUser -> simpUser.getName().equals(friendMe.getAccountOther().getEmail()));
            friendDTO.setOnline(isOnline);
            ImageEntity image = imageRepository.findByEmail(friendMe.getAccountOther().getEmail());
            friendDTO.setOauth2(image.isOauth2());
            if (image.isOauth2()) {
                friendDTO.setAvatar(((String) image.getImage()));
            } else {
                friendDTO.setAvatar(Base64.getEncoder().encodeToString(((Binary) image.getImage()).getData()));
            }
            list.add(friendDTO);
        });
        return list;
    }

    @Override
    public boolean deleteFriend(int id) {
        try {
            AccountEntity accountMe = accountRepository.findByEmail(componentUtils.getEmail());
            accountMe.getFriendsMe().removeIf( data -> data.getAccountOtherId() == id);

            AccountEntity accountOther = accountRepository.findByEmail(componentUtils.getEmail());
            accountOther.getFriendsMe().removeIf(data -> data.getAccountOtherId() == accountMe.getId());

            accountRepository.save(accountMe);
            accountRepository.save(accountOther);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public FriendDTO updateStatus(String email,boolean isOnline) {

        FriendDTO friendDTO = new FriendDTO();
        AccountEntity account = accountRepository.findByEmail(email);
        friendDTO.setId(account.getId());
        friendDTO.setEmail(account.getEmail());
        friendDTO.setName(account.getFullName());
        friendDTO.setOnline(isOnline);
        ImageEntity image = imageRepository.findByEmail(account.getEmail());
        friendDTO.setOauth2(image.isOauth2());
        if(image.isOauth2()) {
            friendDTO.setAvatar(((String) image.getImage()));
        } else {
            friendDTO.setAvatar(Base64.getEncoder().encodeToString(((Binary) image.getImage()).getData()));
        }

        return friendDTO;
    }
}
