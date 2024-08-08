package com.example.storageuser.web;

import com.example.storageuser.domain.User;
import lombok.Getter;
import lombok.NonNull;

public class AuthUser extends org.springframework.security.core.userdetails.User {

    @Getter
    private final User user;

    public AuthUser(@NonNull User user) {
        super(user.getUsername(), user.getPassword(), user.getRoles());
        this.user = user;
    }

    public long id() {
        return user.getId();
    }

    @Override
    public String toString() {
        return "AuthUser:" + user.getId() + '[' + user.getUsername() + ']';
    }
}
