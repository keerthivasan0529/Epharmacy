package com.epharmacy.customer_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private Integer addressId;

    @NotNull(message = "Address name is required")
    private String addressName;

    @NotNull(message = "Address line 1 is required")
    private String addressLine1;

    private String addressLine2;

    @NotNull(message = "Area is required")
    private String area;

    @NotNull(message = "City is required")
    private String city;

    @NotNull(message = "State is required")
    private String state;

    @NotNull(message = "Pincode is required")
    @Pattern(
            regexp = "^[0-9]{6}$",
            message = "Pincode should be 6 digits"
    )
    private String pincode;
}

