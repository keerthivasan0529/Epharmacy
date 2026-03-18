package com.epharmacy.customer_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProfileDto {

    private Integer customerId;

    @NotNull(message = "Name is required")
    @Pattern(
            regexp = "^[A-Za-z]+(\\s[A-Za-z]+)*$",
            message = "Name should contain only alphabets with single space between words"
    )
    private String name;


    @NotNull(message = "Email is required")
    @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.(com|in)$",
            message = "Email should be valid with domain .com or .in"
    )
    private String email;

    @NotNull(message = "Contact number is required")
    @Pattern(
            regexp = "^[6-9][0-9]{9}$",
            message = "Mobile number should start with 6,7,8 or 9 and be 10 digits"
    )
    private String contactNumber;
}
