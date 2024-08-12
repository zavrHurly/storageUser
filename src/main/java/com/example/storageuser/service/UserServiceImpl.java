package com.example.storageuser.service;

import com.example.storageuser.domain.Role;
import com.example.storageuser.domain.User;
import com.example.storageuser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findByUsername(String username) {
        return userRepository.findByName(username);
    }

    @Override
    public User findByName(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(User user) {
        userRepository.deleteById(user.getId());
    }

    public User update(User user) {
        return userRepository.save(user);
    }


    @Override
    public User create(User user) {
        user.setRoles(Collections.singleton(Role.USER));
        return userRepository.save(user);
    }
}
