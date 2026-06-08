package org.example.cafemanagementsystem.controller;

import org.example.cafemanagementsystem.dto.VendorRequestDto;
import org.example.cafemanagementsystem.entity.Vendor;
import org.example.cafemanagementsystem.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private VendorService vendorService;

    @GetMapping("/vendors/pending")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Vendor> getPendingVendors() {

        return vendorService.getPendingVendorRequests();
    }

    @PutMapping("/vendors/{id}/approve")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String approveVendor(
            @PathVariable Long id) {

        return vendorService.approveVendorRequest(id);
    }

    @PutMapping("/vendors/{id}/reject")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String rejectVendor(
            @PathVariable Long id) {

        return vendorService.rejectVendorRequest(id);
    }


}
