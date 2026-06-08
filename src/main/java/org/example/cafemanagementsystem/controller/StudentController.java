package org.example.cafemanagementsystem.controller;

import org.example.cafemanagementsystem.dto.VendorRequestDto;
import org.example.cafemanagementsystem.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private VendorService vendorService;

    @PreAuthorize("hasAuthority('STUDENT')")
    @PostMapping("/vendor/request")
    public String createVendorRequest(@RequestBody VendorRequestDto vendorRequestDto){

        return vendorService.createVendorRequest(vendorRequestDto);
    }
}
