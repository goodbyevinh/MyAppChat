package com.vinh.chat.service.imp;

import com.vinh.chat.constant.NumberConstant;
import com.vinh.chat.dto.DataToken;
import com.vinh.chat.entity.AccountEntity;
import com.vinh.chat.entity.RoleEntity;
import com.vinh.chat.jwt.JwtTokenHelper;
import com.vinh.chat.model.AccountModel;
import com.vinh.chat.payload.request.SignupRequest;
import com.vinh.chat.repository.AuthRepository;
import com.vinh.chat.repository.RoleRepository;
import com.vinh.chat.repository.mongodb.ImageRepository;
import com.vinh.chat.service.AuthService;
import com.vinh.chat.utils.ComponentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Base64;
import java.util.Map;

@Service
public class AuthServiceImp implements AuthService {
    @Autowired
    AuthRepository authRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenHelper jwtTokenHelper;
    @Autowired
    ComponentUtils componentUtils;




    @Override
    public DataToken authentication(String email, String password) {
        UsernamePasswordAuthenticationToken authRequest =
                new UsernamePasswordAuthenticationToken(email, password);
        Authentication auth = authenticationManager.authenticate(authRequest);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(auth);
        String token = jwtTokenHelper.generateToken(email,"authen",securityContext.getAuthentication().getAuthorities().iterator().next().toString() , NumberConstant.expiredDate);
        String refreshToken = jwtTokenHelper.generateToken(email,"refresh",securityContext.getAuthentication().getAuthorities().iterator().next().toString() ,NumberConstant.refreshExpiredDate);



        DataToken dataToken = new DataToken();

        dataToken.setToken(token);
        dataToken.setRefreshToken(refreshToken);
        dataToken.setRole(securityContext.getAuthentication().getAuthorities().iterator().next().toString());
        dataToken.setExpire(NumberConstant.expiredDate);

        return dataToken;
    }



    @Override
    public DataToken refreshToken(String token) {
        DataToken dataToken = new DataToken();
        if(jwtTokenHelper.validateToken(token)){
            String json = jwtTokenHelper.decodeToken(token);
            Map<String, Object> map = componentUtils.getGson().fromJson(json, Map.class);

            if(StringUtils.hasText((map.get("type")).toString()) && map.get("type").toString().equals("refresh")){
                String tokenAuthen = jwtTokenHelper.generateToken(map.get("username").toString(), "authen", map.get("role").toString(), NumberConstant.expiredDate);
                String tokenRefresh = jwtTokenHelper.generateToken(map.get("username").toString(), "refresh", map.get("role").toString(), NumberConstant.refreshExpiredDate);

                dataToken.setToken(tokenAuthen);
                dataToken.setRefreshToken(tokenRefresh);
                dataToken.setRole(map.get("role").toString());
                return dataToken;

            }
        }
        return null;
    }

    @Override
    public DataToken insertUser(SignupRequest signupRequest, String avatar) {

        AccountEntity account = new AccountEntity();
        account.setEmail(signupRequest.getEmail());
        account.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        account.setFullName(signupRequest.getFullname());
        account.setAvatar(avatar);
        RoleEntity role = roleRepository.findById(2).get();
        account.setRole(role);
        try {

            AccountEntity newAccount = authRepository.save(account);
            DataToken dataToken = this.authentication(account.getEmail(), signupRequest.getPassword());
            return dataToken;
        } catch (Exception e) {
            return null;
        }

    }
}
