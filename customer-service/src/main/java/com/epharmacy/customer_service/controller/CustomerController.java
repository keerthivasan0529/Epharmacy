package com.epharmacy.customer_service.controller;

import com.epharmacy.customer_service.dto.*;
import com.epharmacy.customer_service.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customer-api/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    //Logging ?

    @PostMapping(value = "/register")
    public ResponseEntity<String> customerRegisterController(@Valid @RequestBody CustomerDto customerDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customerService.customerRegister(customerDto));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<CustomerDto> customerLoginController(@Valid @RequestBody LoginDto loginDto) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.customerLogin(loginDto));
    }

    @GetMapping(value = "/{customerId}")
    public ResponseEntity<CustomerDto>  viewProfileController(@PathVariable Integer customerId) {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.viewProfile(customerId));
    }

    @PutMapping(value = "/update-profile")
    public ResponseEntity<CustomerDto> updateProfileController(@Valid @RequestBody UpdateProfileDto updateProfileDto) {
        ResponseEntity<CustomerDto> responseEntity = ResponseEntity.status(HttpStatus.OK).body(customerService.updateProfile(updateProfileDto));
        return responseEntity;
    }

    @PutMapping(value = "/upgrade")
    public ResponseEntity<String> upgradeToPrimeController(@RequestBody UpgradeToPrimeDto upgradeToPrimeDto) {
            ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.OK).body(customerService.upgradeToPrime(upgradeToPrimeDto.getCustomerId(), upgradeToPrimeDto.getPlanId()));
            return responseEntity;
    }

    @GetMapping(value = "/view-addresses/{customerId}")
    public ResponseEntity<List<AddressDto>>  viewAddressesController(@PathVariable Integer customerId) {
         ResponseEntity<List<AddressDto>> responseEntity = ResponseEntity.status(HttpStatus.OK).body(customerService.viewAddresses(customerId));
        return responseEntity;
    }

    @PostMapping(value = "/add-address/{customerId}")
    public ResponseEntity<String> addAddressController(@PathVariable Integer customerId,@Valid @RequestBody AddressDto addressDto) {
        ResponseEntity<String> response = ResponseEntity.status(HttpStatus.CREATED).body(customerService.addAddress(customerId,addressDto));
        return response;
    }

    @PutMapping(value = "/change-password")
    public ResponseEntity<String> changePasswordController(@Valid @RequestBody ChangePasswordDto changePasswordDto) {
        ResponseEntity<String> response = ResponseEntity.status(HttpStatus.OK).body(customerService.changePassword(changePasswordDto));
                return response;
    }
}
