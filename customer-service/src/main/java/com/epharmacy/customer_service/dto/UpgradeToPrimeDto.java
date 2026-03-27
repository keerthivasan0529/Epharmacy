package com.epharmacy.customer_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpgradeToPrimeDto {

    private Integer customerId;
    private Integer planId;

}
