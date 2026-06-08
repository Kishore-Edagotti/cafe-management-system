package org.example.cafemanagementsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="vendors")
@Getter
@Setter
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String businessName;

    private String phoneNumber;

    private String description;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private VendorStatus status;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
