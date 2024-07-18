package com.example.storageuser.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="users")
@Getter
@Setter
public class User extends AbstractEntity implements Serializable {

    @NotBlank
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

    @Column(name = "enabled")
    private boolean enabled = true;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    private List<Role> roles;

}
