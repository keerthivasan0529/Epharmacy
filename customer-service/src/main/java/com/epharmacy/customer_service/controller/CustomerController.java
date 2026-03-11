package com.epharmacy.customer_service.controller;

import com.epharmacy.customer_service.dto.CustomerDto;
import com.epharmacy.customer_service.dto.LoginDto;
import com.epharmacy.customer_service.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/customer-api")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    //Logging ?

    @PostMapping(value = "/register")
    public ResponseEntity<String> customerRegister(@RequestBody CustomerDto customerDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customerService.customerRegister(customerDto));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<CustomerDto> customerLogin(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(customerService.customerLogin(loginDto));
    }

}
