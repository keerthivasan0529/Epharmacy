package com.epharmacy.customer_service.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

//No need to give data validation in entity class (as it makes entity tightly coupled) as it is done in dto
// class and entity class is used to  map the data to database and dto class is used to transfer the
// data from client to server and vice versa.

@Data
@NoArgsConstructor   // ModelMapper needs empty constructor
@AllArgsConstructor  // useful for testing
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
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

    @Column(name = "health_coins")
    private Integer healthCoins = 0;

    // One customer can have many addresses
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Address> addressList;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<PasswordHistory> passwordHistoryList;
}
