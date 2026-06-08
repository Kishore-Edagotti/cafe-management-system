package org.example.cafemanagementsystem.controller;

import org.example.cafemanagementsystem.dto.LoginRequest;
import org.example.cafemanagementsystem.dto.RegisterRequest;
import org.example.cafemanagementsystem.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private AuthService authService;

    @GetMapping("/hello")
    public String hello(){
        return "HELLO";
    }

    @PostMapping("/auth/register")
    public String register(@RequestBody RegisterRequest request){
     return    authService.register(request);
    }

    @PostMapping("/auth/login")
    public String login(@RequestBody LoginRequest request){
        return    authService.login(request);
    }
}
