package com.epharmacy.customer_service.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

//No need to give data validation in entity class (as it makes entity tightly coupled) as it is done in dto
// class and entity class is used to  map the data to database and dto class is used to transfer the
// data from client to server and vice versa.

@Data
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    private String name;
    private String email;
    private String password;
    private LocalDate dob;
    @Column(name = "contact_number")
    private String contactNumber;
    private String gender;
    private String plan = "Regular";
    @Column(name = "plan_expiry_date")
    private LocalDate planExpiryDate;
    private Integer healthCoins = 0;
}
