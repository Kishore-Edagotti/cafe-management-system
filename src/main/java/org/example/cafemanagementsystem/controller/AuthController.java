package org.example.cafemanagementsystem.controller;

import org.example.cafemanagementsystem.dto.LoginRequest;
import org.example.cafemanagementsystem.dto.RegisterRequest;
import org.example.cafemanagementsystem.dto.VendorRequestDto;
import org.example.cafemanagementsystem.service.AuthService;
import org.example.cafemanagementsystem.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private VendorService vendorService;

    @PreAuthorize("hasAuthority('STUDENT')")
    @GetMapping("/student/hello")
    public String hello(){

        return "HELLO";
    }

    @PreAuthorize("hasAuthority('STUDENT')")
    @PostMapping("/student/hello")
    public String hello(@RequestBody VendorRequestDto vendorRequestDto){

        return "HELLO"+vendorService.createVendorRequest(vendorRequestDto);
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
