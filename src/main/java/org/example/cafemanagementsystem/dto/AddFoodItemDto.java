package org.example.cafemanagementsystem.dto;
import lombok.Data;

@Data
public class AddFoodItemDto {


    private String foodName;

    private Double price;

    private String description;

    private String imageUrl;

}
