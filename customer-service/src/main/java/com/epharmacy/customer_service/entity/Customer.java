package com.epharmacy.customer_service.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.Length;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cudtomerId;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private LocalDate dob;
    @Column(name="contact_number",length = 10)
    @NotNull
    private String contactNumber;
    private String gender;
    @Column(length = 20)
    private String plan ="Regular";
    @Column(name="plan_expiry_date")
    private LocalDate planExpiryDate;
    private Integer healthCoins=0;
}
