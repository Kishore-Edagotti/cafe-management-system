package org.example.cafemanagementsystem.dto;

import lombok.Data;

@Data
public class FoodItemResponseDto {

    private Long id;

    private String foodName;

    private Double price;

    private String description;

    private String imageUrl;

    private boolean available;

    private String vendorName;
}