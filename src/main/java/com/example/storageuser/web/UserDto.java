package com.example.storageuser.web;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {

    @NotBlank
    private String username;

    @NotBlank
    private String surname;

    @NotBlank
    @Email
    private String email;

    private boolean allowsMarketing;

    @Size(min = 8, max = 64, message = "Password must be 8-64 char long")
    private String password;

}
