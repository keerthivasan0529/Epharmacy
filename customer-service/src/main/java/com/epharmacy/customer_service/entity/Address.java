package com.epharmacy.customer_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;
    @Column(name = "address_name")
    private String addressName;
    @Column(name = "address_line1")
    private String addressLine1;
    @Column(name = "address_line2")
    private String addressLine2;

    private String area;

    private String city;

    private String state;

    private String pincode;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
