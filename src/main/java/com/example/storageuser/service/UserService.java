package com.example.storageuser.service;

import com.example.storageuser.domain.User;
import com.example.storageuser.web.UserDtoToView;

import java.util.List;

public interface UserService {

    List<User> getAll();

    List<UserDtoToView> getAllDto();

    List<User> findByUsername(String username);

    List<UserDtoToView> findDtoByUsername(String username);

    User findByName(String username);

    User findById(Long id);

    void delete(User user);

    User update(User user);

    User create(User user);
}
