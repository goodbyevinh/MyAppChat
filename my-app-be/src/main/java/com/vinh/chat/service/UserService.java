package com.vinh.chat.service;

import com.vinh.chat.dto.AccountDTO;
import com.vinh.chat.entity.AccountEntity;

public interface UserService {
    AccountEntity checkLogin(String email);
    AccountDTO getAccount();
}
