package org.example.cafemanagementsystem.service;

import org.example.cafemanagementsystem.dto.AddFoodItemDto;
import org.example.cafemanagementsystem.dto.FoodItemResponseDto;
import org.example.cafemanagementsystem.entity.FoodItem;
import org.example.cafemanagementsystem.entity.User;
import org.example.cafemanagementsystem.entity.Vendor;
import org.example.cafemanagementsystem.entity.VendorStatus;
import org.example.cafemanagementsystem.repository.FoodItemRepo;
import org.example.cafemanagementsystem.repository.UserRepo;
import org.example.cafemanagementsystem.repository.VendorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FoodItemService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private VendorRepo vendorRepo;

    @Autowired
    private FoodItemRepo foodItemRepo;

    public String addFoodItem(AddFoodItemDto dto) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String username = authentication.getName();

        Optional<User> optionalUser =
                userRepo.findByUsername(username);

        if (optionalUser.isEmpty()) {
            return "User not found";
        }

        User user = optionalUser.get();

        Optional<Vendor> optionalVendor =
                vendorRepo.findByUser(user);

        if (optionalVendor.isEmpty()) {
            return "Vendor not found";
        }

        Vendor vendor = optionalVendor.get();

        if (!vendor.getStatus().equals(VendorStatus.APPROVED)) {
            return "Vendor is not approved";
        }

        FoodItem foodItem = new FoodItem();

        foodItem.setFoodName(dto.getFoodName());
        foodItem.setPrice(dto.getPrice());
        foodItem.setDescription(dto.getDescription());
        foodItem.setImageUrl(dto.getImageUrl());

        foodItem.setAvailable(true);

        foodItem.setVendor(vendor);

        foodItemRepo.save(foodItem);

        return dto.getFoodName() + " added successfully";
    }



    public List<FoodItemResponseDto>getVendorMenu() {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String username = authentication.getName();

        User user = userRepo.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Vendor vendor = vendorRepo.findByUser(user)
                .orElseThrow(() ->
                        new RuntimeException("Vendor not found"));

        List<FoodItemResponseDto> response = new ArrayList<>();
        List<FoodItem> foodItemList = foodItemRepo.findAllByVendor(vendor);

        for(FoodItem foodItem: foodItemList){
            FoodItemResponseDto dto = new FoodItemResponseDto();

            dto.setId(foodItem.getId());
            dto.setFoodName(foodItem.getFoodName());
            dto.setPrice(foodItem.getPrice());
            dto.setDescription(foodItem.getDescription());
            dto.setImageUrl(foodItem.getImageUrl());
            dto.setAvailable(foodItem.isAvailable());

            dto.setVendorName(
                    foodItem.getVendor().getBusinessName()
            );
            response.add(dto);
        }

        return response;
    }


    public String updateFoodItem(Long foodItemId,
                                 AddFoodItemDto dto) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String username = authentication.getName();

        User user = userRepo.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Vendor vendor = vendorRepo.findByUser(user)
                .orElseThrow(() ->
                        new RuntimeException("Vendor not found"));

        FoodItem foodItem = foodItemRepo
                .findByIdAndVendor(foodItemId, vendor)
                .orElseThrow(() ->
                        new RuntimeException("Food Item not found"));

        foodItem.setFoodName(dto.getFoodName());
        foodItem.setPrice(dto.getPrice());
        foodItem.setDescription(dto.getDescription());
        foodItem.setImageUrl(dto.getImageUrl());

        foodItemRepo.save(foodItem);

        return foodItem.getFoodName() + " updated successfully";
    }


    public String disableFoodItem(Long foodItemId) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String username = authentication.getName();

        User user = userRepo.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Vendor vendor = vendorRepo.findByUser(user)
                .orElseThrow(() ->
                        new RuntimeException("Vendor not found"));

        FoodItem foodItem = foodItemRepo
                .findByIdAndVendor(foodItemId, vendor)
                .orElseThrow(() ->
                        new RuntimeException("Food Item not found"));

        foodItem.setAvailable(false);

        foodItemRepo.save(foodItem);

        return foodItem.getFoodName() + " disabled successfully";
    }
    public String enableFoodItem(Long foodItemId) {

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String username = authentication.getName();

        User user = userRepo.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Vendor vendor = vendorRepo.findByUser(user)
                .orElseThrow(() ->
                        new RuntimeException("Vendor not found"));

        FoodItem foodItem = foodItemRepo
                .findByIdAndVendor(foodItemId, vendor)
                .orElseThrow(() ->
                        new RuntimeException("Food Item not found"));

        foodItem.setAvailable(true);

        foodItemRepo.save(foodItem);

        return foodItem.getFoodName() + " enabled successfully";
    }
}
