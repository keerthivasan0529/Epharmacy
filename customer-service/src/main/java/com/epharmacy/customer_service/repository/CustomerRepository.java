package com.epharmacy.customer_service.repository;
import com.epharmacy.customer_service.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    public Optional<Customer> findByEmail(String email);

}
