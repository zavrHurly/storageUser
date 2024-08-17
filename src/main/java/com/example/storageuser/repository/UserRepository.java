package com.example.storageuser.repository;

import com.example.storageuser.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {

    @Query("SELECT u FROM User u WHERE u.username LIKE CONCAT('%', :name, '%')")
    List<User> findByName(@Param("name") String name);

    @Query("SELECT u FROM User u WHERE u.username = :name")
    User findByUserName(String name);
}
