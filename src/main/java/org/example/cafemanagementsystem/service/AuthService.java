package org.example.cafemanagementsystem.service;
import org.example.cafemanagementsystem.dto.RegisterRequest;
import org.example.cafemanagementsystem.entity.Role;
import org.example.cafemanagementsystem.entity.User;
import org.example.cafemanagementsystem.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class AuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public String register(RegisterRequest registerRequest){

        if((!userRepo.existsByUsername(registerRequest.getUsername()) && !userRepo.existsByEmail(registerRequest.getEmail()))){
            User user = new User();
            user.setUsername(registerRequest.getUsername());
            user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            user.setEmail(registerRequest.getEmail());
            user.setRole(Role.STUDENT);
            userRepo.save(user);
            return "User Created";
        }

        return "Registration Failed: Username or email exits";
    }
}
