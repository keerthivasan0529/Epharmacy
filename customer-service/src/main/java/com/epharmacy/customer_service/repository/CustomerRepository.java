package com.epharmacy.customer_service.repository;
import com.epharmacy.customer_service.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Optional means:
// ✅ Customer found → Optional has value
// ❌ Customer not found → Optional is empty

// Interface is Public by Default, so no need to write public keyword here. But it is a good practice to write it for better readability and understanding of the code.

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

     Optional<Customer> findByEmail(String email);

}
