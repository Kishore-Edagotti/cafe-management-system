package org.example.cafemanagementsystem.service;

import org.example.cafemanagementsystem.dto.VendorRequestDto;
import org.example.cafemanagementsystem.entity.Role;
import org.example.cafemanagementsystem.entity.User;
import org.example.cafemanagementsystem.entity.Vendor;
import org.example.cafemanagementsystem.entity.VendorStatus;
import org.example.cafemanagementsystem.repository.UserRepo;
import org.example.cafemanagementsystem.repository.VendorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private VendorRepo vendorRepo;

    public String createVendorRequest(VendorRequestDto dto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> userOptional =
                userRepo.findByUsername(username);

        User user = userOptional.orElseThrow(
                () -> new RuntimeException("User not found")
        );


        if (vendorRepo.existsByUser(user)){
            return "Vendor request already exists";
        }
        Vendor vendor = new Vendor();

        vendor.setBusinessName(dto.getBusinessName());
        vendor.setPhoneNumber(dto.getPhoneNumber());
        vendor.setDescription(dto.getDescription());
        vendor.setImageUrl(dto.getImageUrl());

        vendor.setStatus(VendorStatus.PENDING);

            vendor.setUser(user);

            vendorRepo.save(vendor);



        return "Status=Pending";
    }

    public String approveVendorRequest(Long id){
        Optional<Vendor> optionalVendor = vendorRepo.findById(id);

        if (optionalVendor.isPresent()){
         Vendor   vendor  = optionalVendor.get();
            if(vendor.getStatus() == VendorStatus.APPROVED){
                return "Vendor already approved";
            }
         vendor.setStatus(VendorStatus.APPROVED);

         User user = vendor.getUser();
         user.setRole(Role.VENDOR);

         userRepo.save(user);
         vendorRepo.save(vendor);

         return "Vendor approval sucesses";
        }



        return "Vendor approval failed";

    }

    public List<Vendor> getPendingVendorRequests(){

        return vendorRepo.findAllByStatus(VendorStatus.APPROVED);

    }


    public List<Vendor> getApprovedVendors() {
        return vendorRepo.findAllByStatus(
                VendorStatus.APPROVED
        );
    }

    public List<Vendor> getRejectedVendors() {
        return vendorRepo.findAllByStatus(
                VendorStatus.REJECTED
        );
    }

    public String rejectVendorRequest(Long id) {

        return "approval rejeced";
    }
}
