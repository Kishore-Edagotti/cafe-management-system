package org.example.cafemanagementsystem.service;
import org.example.cafemanagementsystem.dto.LoginRequest;
import org.example.cafemanagementsystem.dto.RegisterRequest;
import org.example.cafemanagementsystem.entity.Role;
import org.example.cafemanagementsystem.entity.User;
import org.example.cafemanagementsystem.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class AuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;


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

    public String login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),request.getPassword()));
        if(authentication.isAuthenticated()){
            return "Login Success";
        }
        return "Invalid Credentials";
    }
}
