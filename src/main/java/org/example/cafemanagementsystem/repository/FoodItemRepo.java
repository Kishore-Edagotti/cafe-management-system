package org.example.cafemanagementsystem.repository;

import org.example.cafemanagementsystem.entity.FoodItem;
import org.example.cafemanagementsystem.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodItemRepo extends JpaRepository<FoodItem,Long> {

    List<FoodItem> findAllByVendor(Vendor vendor);

    List<FoodItem> id(Long id);

    Optional<FoodItem> findByIdAndVendor(Long id, Vendor vendor);
}
