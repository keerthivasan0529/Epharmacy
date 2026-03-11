package com.epharmacy.customer_service.service;

import com.epharmacy.customer_service.dto.CustomerDto;
import com.epharmacy.customer_service.dto.LoginDto;

public interface CustomerService {
    public String customerRegister(CustomerDto customerDto);
    public CustomerDto customerLogin(LoginDto loginDto);
}
