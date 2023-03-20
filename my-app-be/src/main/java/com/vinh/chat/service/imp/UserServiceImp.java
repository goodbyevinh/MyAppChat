package com.vinh.chat.service.imp;

import com.vinh.chat.dto.AccountDTO;
import com.vinh.chat.entity.AccountEntity;
import com.vinh.chat.model.AccountModel;
import com.vinh.chat.model.mongodb.ImageEntity;
import com.vinh.chat.repository.AuthRepository;
import com.vinh.chat.repository.mongodb.ImageRepository;
import com.vinh.chat.service.UserService;
import com.vinh.chat.utils.ComponentUtils;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    AuthRepository authRepository;
    @Autowired
    ComponentUtils componentUtils;
    @Autowired
    ImageRepository imageRepository;
    @Override
    public AccountEntity checkLogin(String email) {
        return authRepository.findByEmail(email);
    }

    @Override
    public AccountDTO getAccount() {
        AccountEntity account = authRepository.findByEmail(componentUtils.getEmail());
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setEmail(account.getEmail());
        accountDTO.setName(account.getFullName());
        accountDTO.setId(account.getId());
        accountDTO.setOauth2(account.isOauth2());
        ImageEntity image = imageRepository.findByEmail(account.getEmail());
        if(accountDTO.isOauth2()) {
            accountDTO.setAvatar(((String) image.getImage()));
        } else {
            String avatar = Base64.getEncoder().encodeToString(((Binary) image.getImage()).getData());
            accountDTO.setAvatar(avatar);
        }

        return accountDTO;
    }
}
