package com.example.storageuser.service;

import com.example.storageuser.domain.Role;
import com.example.storageuser.domain.User;
import com.example.storageuser.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        List<User> listFromDb = (List<User>) userRepository.findAll();
        log.info("IN getAll - {} users found", listFromDb.size());
        return listFromDb;
    }

    @Override
    public List<User> findByUsername(String username) {
        List<User> result = userRepository.findByName(username);
        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public User findByName(String username) {
        User result = userRepository.findByUserName(username);
        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }

        log.info("IN findById - user: {} found by id: {}", result);
        return result;
    }

    @Override
    public void delete(User user) {
        userRepository.deleteById(user.getId());
        log.info("IN delete - user with id: {} successfully deleted");
    }

    public User update(User user) {
        return userRepository.save(user);
    }


    @Override
    public User create(User user) {
        user.setEnabled(true);
        user.setRoles(Collections.singletonList(Role.USER));
        User registeredUser = userRepository.save(user);

        log.info("IN register - user: {} successfully registered", registeredUser);

        return registeredUser;
    }
}
