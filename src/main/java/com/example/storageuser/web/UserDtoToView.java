package com.example.storageuser.web;

import com.example.storageuser.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDtoToView {

    private Long id;

    private String username;

    private String surname;

    private String fatherName;

    private LocalDate dateOfBirth;

    private String email;

    private String callNumber;

    public UserDtoToView(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.surname = user.getSurname();
        this.fatherName = user.getFatherName();
        this.dateOfBirth = user.getDateOfBirth();
        this.email = user.getEmail();
        this.callNumber = user.getCallNumber();
    }
}
