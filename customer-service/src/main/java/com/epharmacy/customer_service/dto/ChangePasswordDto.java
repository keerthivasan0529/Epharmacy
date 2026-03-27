package com.epharmacy.customer_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordDto {
    private Integer customerId;
    @NotBlank
    private String oldPassword;
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{7,20}$",
            message = "Password must have uppercase, lowercase, digit, special char, min 7 max 20 chars"
    )
    private String newPassword;
    @NotBlank
    private String confirmPassword;
}
