package com.vinh.chat.utils;

import com.google.gson.Gson;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.stereotype.Component;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Component
public class ComponentUtils {
    public String getEmail() {
        var authentication = getContext().getAuthentication();
        return authentication instanceof AnonymousAuthenticationToken ? null
                :authentication.getPrincipal().toString();
    }
    public Gson getGson() {
        return new Gson();
    }
}
