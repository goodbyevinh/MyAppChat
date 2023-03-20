package com.vinh.chat.service;

import com.vinh.chat.dto.DataToken;
import com.vinh.chat.entity.AccountEntity;
import com.vinh.chat.payload.request.SignupRequest;

public interface AuthService {



    DataToken authentication(String email, String password);
    DataToken refreshToken(String token);

    DataToken insertUser(SignupRequest signupRequest, String avatar);


}
