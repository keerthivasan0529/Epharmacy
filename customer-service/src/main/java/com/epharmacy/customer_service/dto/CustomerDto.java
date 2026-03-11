package com.epharmacy.customer_service.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;


@Data
@AllArgsConstructor
public class CustomerDto {

    private Integer customerId;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private LocalDate dob;
    @NotNull
    @Column(length = 20)
    private String contactNumber;
    private String gender;
    private String plan = "Regular";
    private LocalDate planExpiryDate;
    private Integer healthCoins = 0;


}
