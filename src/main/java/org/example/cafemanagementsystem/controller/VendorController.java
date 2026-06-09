package org.example.cafemanagementsystem.controller;

import org.example.cafemanagementsystem.dto.VendorRequestDto;
import org.example.cafemanagementsystem.entity.Vendor;
import org.example.cafemanagementsystem.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendor")
public class VendorController {
    @Autowired
    private VendorService vendorService;

    @GetMapping("/profile/{id}")
    @PreAuthorize("hasAuthority('VENDOR')")
    public VendorRequestDto getProfile(@PathVariable Long id) {

        return vendorService.getVendorProfile(id);
    }

    @PutMapping("/profile/{id}")
    @PreAuthorize("hasAuthority('VENDOR')")
    public String updateProfile(
            @PathVariable Long id,
            @RequestBody VendorRequestDto dto) {

        return vendorService.updateVendorProfile(id,dto);
    }



}
