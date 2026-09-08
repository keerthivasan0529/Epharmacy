package com.epharmacy.medicine_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicineDto {

    private Integer medicineId;
    private String medicineName;
    private String manufacturer;
    private String category;
    private LocalDate manufacturingDate;
    private LocalDate expiryDate;
    private Double price;

    // This will be calculated based on expiry date
    // NOT just copied from DB — see MedicineServiceImpl
    private Integer discountPercent;

    // "In Stock" or "Out of Stock" — derived from stockQuantity
    // We show user-friendly text, not the raw number
    private String stockStatus;
}
