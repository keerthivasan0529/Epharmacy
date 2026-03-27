package com.epharmacy.customer_service.service;

import com.epharmacy.customer_service.dto.*;

import java.util.List;


//US04 - Register
//US05 - Login
//US07 - View Profile
//US07 - Update Profile
//US07 - Upgrade to Prime
//US08 - Change Password
//View Addresses
//Add Address

public interface CustomerService {

    String customerRegister(CustomerDto customerDto);

    CustomerDto customerLogin(LoginDto loginDto);

    // US07 - View customer profile
    CustomerDto viewProfile(Integer customerId);

    // US07 - Update customer profile
    CustomerDto updateProfile(UpdateProfileDto updateProfileDto);

    // US07 - Upgrade to Prime
    String upgradeToPrime(Integer customerId, Integer planId);

    // US08 - Change password
    String changePassword(ChangePasswordDto changePasswordDto);

    // View all addresses of customer
    List<AddressDto> viewAddresses(Integer customerId);

    // Add new address for customer
    String addAddress(Integer customerId, AddressDto addressDto);
}
