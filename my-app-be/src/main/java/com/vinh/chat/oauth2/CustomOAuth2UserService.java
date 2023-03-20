package com.vinh.chat.oauth2;

import com.vinh.chat.entity.AccountEntity;
import com.vinh.chat.entity.RoleEntity;
import com.vinh.chat.model.OAuth2UserModel;
import com.vinh.chat.model.mongodb.ImageEntity;
import com.vinh.chat.repository.AccountRepository;
import com.vinh.chat.repository.RoleRepository;
import com.vinh.chat.repository.mongodb.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ImageRepository imageRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        OAuth2UserModel oAuth2UserModel = OAuth2UserToModel(oAuth2User);

        AccountEntity account = accountRepository.findByEmail(oAuth2UserModel.getEmail());
        boolean isExist = account != null;

        if (isExist) {
            if (account.isOauth2()) {
                //account = updateAccount(oAuth2UserModel, account);
                account.setFullName(oAuth2UserModel.getName());
                ImageEntity imageEntity = imageRepository.findByEmail(oAuth2UserModel.getEmail());
                imageEntity.setImage(oAuth2UserModel.getAvatar());
                imageRepository.save(imageEntity);
                account.setAvatar(imageEntity.getId());
                account = accountRepository.save(account);
            } else {

            }
        } else {
            //account = insertAccount(oAuth2UserModel);

            AccountEntity newAccount = new AccountEntity();
            newAccount.setOauth2(true);
            newAccount.setFullName(oAuth2UserModel.getName());

            ImageEntity image = new ImageEntity();
            image.setEmail(oAuth2UserModel.getEmail());
            image.setOauth2(true);
            image.setImage(oAuth2UserModel.getAvatar());

            ImageEntity newImg = imageRepository.save(image);
            newAccount.setAvatar(newImg.getId());
            newAccount.setEmail(oAuth2UserModel.getEmail());
            account = accountRepository.save(newAccount);
        }
        return new CustomOAuth2User(oAuth2User);
    }

    private OAuth2UserModel OAuth2UserToModel(OAuth2User oAuth2User) {
        OAuth2UserModel oAuth2UserModel = new OAuth2UserModel();
        oAuth2UserModel.setEmail((String) oAuth2User.getAttributes().get("email"));
        oAuth2UserModel.setName((String) oAuth2User.getAttributes().get("name"));
        oAuth2UserModel.setAvatar((String) oAuth2User.getAttributes().get("picture"));
        return oAuth2UserModel;
    }
    private AccountEntity insertAccount (OAuth2UserModel model){
        AccountEntity account = new AccountEntity();
        account.setOauth2(true);
        account.setFullName(model.getName());
        account.setAvatar(model.getAvatar());
        account.setEmail(model.getEmail());
        RoleEntity role = roleRepository.findById(2).get();
        account.setRole(role);
        return accountRepository.save(account);
    }
    private AccountEntity updateAccount (OAuth2UserModel model, AccountEntity account){
        account.setFullName(model.getName());
        account.setAvatar(model.getAvatar());
        return accountRepository.save(account);
    }

}
