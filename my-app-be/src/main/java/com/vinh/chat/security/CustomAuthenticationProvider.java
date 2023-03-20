package com.vinh.chat.security;

import com.vinh.chat.entity.AccountEntity;
import com.vinh.chat.service.AuthService;
import com.vinh.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserService userService;


    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        AccountEntity account = userService.checkLogin(username);

        if (account != null ) {
            boolean isMatchPassword = passwordEncoder.matches(password, account.getPassword());
            if (isMatchPassword) {
                return new UsernamePasswordAuthenticationToken(account.getId(), account.getPassword(), AuthorityUtils.createAuthorityList(account.getRole().getName()));
            } else {
                throw new BadCredentialsException("Bad credentials");
            }
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
