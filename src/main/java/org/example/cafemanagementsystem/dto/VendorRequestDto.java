    package org.example.cafemanagementsystem.dto;

    import lombok.Getter;
    import lombok.Setter;

    @Setter
    @Getter
    public class VendorRequestDto {

        private String businessName;

        private String phoneNumber;

        private String description;

        private String imageUrl;
    }
