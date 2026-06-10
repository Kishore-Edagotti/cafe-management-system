package org.example.cafemanagementsystem.controller;
import org.example.cafemanagementsystem.dto.AddFoodItemDto;
import org.example.cafemanagementsystem.dto.FoodItemResponseDto;
import org.example.cafemanagementsystem.dto.VendorRequestDto;
import org.example.cafemanagementsystem.service.FoodItemService;
import org.example.cafemanagementsystem.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendor")
public class VendorController {
    @Autowired
    private VendorService vendorService;
    @Autowired
    private FoodItemService foodItemService;


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

    @PostMapping("/menu")
    @PreAuthorize("hasAuthority('VENDOR')")
    public String addFoodItem(
            @RequestBody AddFoodItemDto dto) {

        return foodItemService.addFoodItem(dto);
    }
    @GetMapping("/menu")
    @PreAuthorize("hasAuthority('VENDOR')")
    public List<FoodItemResponseDto> getVendorMenu() {

        return foodItemService.getVendorMenu();
    }

    @PutMapping("/menu/{id}")
    @PreAuthorize("hasAuthority('VENDOR')")
    public String updateFoodItem(
            @PathVariable Long id,
            @RequestBody AddFoodItemDto dto
    ) {
        return foodItemService.updateFoodItem(id, dto);
    }

    @PatchMapping("/menu/{id}/disable")
    @PreAuthorize("hasAuthority('VENDOR')")
    public String disableFoodItem(@PathVariable Long id) {

        return foodItemService.disableFoodItem(id);
    }

    @PatchMapping("/menu/{id}/enable")
    @PreAuthorize("hasAuthority('VENDOR')")
    public String enableFoodItem(@PathVariable Long id) {

        return foodItemService.enableFoodItem(id);
    }




    @DeleteMapping("/menu/delete/{id}")
    @PreAuthorize("hasAuthority('VENDOR')")
    public String deleteFoodItem(@PathVariable Long id) {

        return foodItemService.deleteFoodItem(id);
    }



}
