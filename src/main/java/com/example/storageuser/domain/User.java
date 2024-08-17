package com.example.storageuser.domain;

import com.example.storageuser.web.UserDtoToRegister;
import com.example.storageuser.web.UserDtoToView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonIgnore
    private Long id;

    private String username;

    private String surname;

    private String fatherName;

    private LocalDate dateOfBirth;

    private String email;

    private String callNumber;

    @Column(name = "password")
    @Size(max = 256)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    private Set<Role> roles;

    public User (UserDtoToRegister userDto) {
        this.username = userDto.getUsername();
        this.surname = userDto.getSurname();
        this.email = userDto.getEmail();
        this.password = userDto.getPassword();
    }

    public User (UserDtoToView userDto) {
        this.username = userDto.getUsername();
        this.surname = userDto.getSurname();
        this.email = userDto.getEmail();
        this.fatherName = userDto.getFatherName();
        this.dateOfBirth = userDto.getDateOfBirth();
        this.callNumber = userDto.getCallNumber();
    }

}
