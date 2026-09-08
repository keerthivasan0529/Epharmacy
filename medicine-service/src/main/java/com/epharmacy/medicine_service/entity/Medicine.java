package com.epharmacy.medicine_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "medicine")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicine_id")
    private Integer medicineId;

    @Column(name = "medicine_name")
    private String medicineName;

    private String manufacturer;

    private String category;

    @Column(name = "manufacturing_date")
    private LocalDate manufacturingDate;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    private Double price;

    @Column(name = "discount_percent")
    private Integer discountPercent;

    @Column(name = "stock_quantity")
    private Integer stockQuantity;
}
