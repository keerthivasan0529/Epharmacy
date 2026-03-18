package com.epharmacy.customer_service.repository;

import com.epharmacy.customer_service.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

//For these TWO endpoints in Customer Microservice:
//
//        1. GET /customer/view-addresses/{customerId}
//   → Customer wants to see all his addresses
//   → Need to fetch all addresses from DB
//   → findByCustomerCustomerId() does this!
//
//        2. POST /customer/add-address/{customerId}
//        → save() from JpaRepository handles this
//        → No custom method needed!

public interface AddressRepository extends JpaRepository<Address, Integer> {

    List<Address> findByCustomerCustomerId(Integer customerId);

}
