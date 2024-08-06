package com.example.storageuser.repository;

import com.example.storageuser.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {

    @Query("from User u where concat(u.username, ' ', u.surname, ' ', u.fatherName) like concat('%', :name, '%')")
    List<User> findByName(@Param("name") String name);

    @Query("SELECT u FROM User u WHERE u.username = :name")
    User findByUserName(String name);
}
