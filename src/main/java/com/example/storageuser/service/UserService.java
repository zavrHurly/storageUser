package com.example.storageuser.service;

import com.example.storageuser.domain.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    List<User> findByUsername(String username);

    User findByName(String username);

    User findById(Long id);

    void delete(User user);

    User update(User user);

    User create(User user);
}
