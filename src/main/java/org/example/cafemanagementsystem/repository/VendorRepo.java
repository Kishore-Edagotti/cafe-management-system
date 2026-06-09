package org.example.cafemanagementsystem.repository;

import org.example.cafemanagementsystem.entity.User;
import org.example.cafemanagementsystem.entity.Vendor;
import org.example.cafemanagementsystem.entity.VendorStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VendorRepo extends JpaRepository<Vendor,Long> {


    boolean existsByUser(User user);

    List<Vendor> findAllByStatus(VendorStatus vendorStatus);

    Optional<Vendor> findByUser(User user);
}
