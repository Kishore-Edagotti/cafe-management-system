package org.example.cafemanagementsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.example.cafemanagementsystem.entity.Role;

@Data
public class RegisterRequest {

    @NotBlank(message = "Username is required")
    private  String username;

    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    private Role role;
}
