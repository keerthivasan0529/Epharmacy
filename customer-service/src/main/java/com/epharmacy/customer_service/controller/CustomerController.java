package com.epharmacy.customer_service.controller;

import com.epharmacy.customer_service.dto.CustomerDto;
import com.epharmacy.customer_service.dto.LoginDto;
import com.epharmacy.customer_service.dto.UpdateProfileDto;
import com.epharmacy.customer_service.entity.Customer;
import com.epharmacy.customer_service.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/customer-api/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    //Logging ?

    @PostMapping(value = "/register")
    public ResponseEntity<String> customerRegister(@Valid @RequestBody CustomerDto customerDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customerService.customerRegister(customerDto));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<CustomerDto> customerLogin(@Valid @RequestBody LoginDto loginDto) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.customerLogin(loginDto));
    }

    @GetMapping(value = "/{customerId}")
    public ResponseEntity<CustomerDto>  viewProfile(@PathVariable Integer customerId) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.viewProfile(customerId));
    }

    @PutMapping(value = "/update-profile")
    public ResponseEntity<CustomerDto> updateProfile(@Valid @RequestBody UpdateProfileDto updateProfileDto) {
        ResponseEntity<CustomerDto> responseEntity = ResponseEntity.status(HttpStatus.OK).body(customerService.updateProfile(updateProfileDto));
        return responseEntity;
    }

//    @PutMapping(value = "/upgrade")
//    public ResponseEntity<String> upgradeToPrime(@RequestBody Integer customerId, @RequestBody Integer planId) {
//            ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.OK).body(customerService.upgradeToPrime(customerId, planId));
//            return responseEntity;
//    }
}
