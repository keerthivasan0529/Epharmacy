package com.epharmacy.customer_service.repository;

import com.epharmacy.customer_service.entity.PasswordHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PasswordHistoryRepository extends JpaRepository<PasswordHistory,Integer> {


//    "Find top 3 by customer id
//    ordered by date descending"

//    find    → find
//    Top3    → top 3 records
//    By      → where
//    CustomerCustomerId → customer's id
//    OrderBy → ordered by
//    ChangedPasswordDate → date field
//    Desc    → newest first

    List<PasswordHistory> findTop3ByCustomerCustomerIdOrderByChangedPasswordDateDesc(Integer customerId) ;

}
