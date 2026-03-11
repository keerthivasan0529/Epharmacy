package com.epharmacy.customer_service.service;

import com.epharmacy.customer_service.utility.Constants;
import org.modelmapper.ModelMapper;
import com.epharmacy.customer_service.dto.CustomerDto;
import com.epharmacy.customer_service.dto.LoginDto;
import com.epharmacy.customer_service.entity.Customer;
import com.epharmacy.customer_service.repository.CustomerRepository;
import com.epharmacy.customer_service.utility.CustomerNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ModelMapper modelmapper;

    @Override
    public String customerRegister(CustomerDto customerDto) {

        Optional<Customer> existingCustomer = customerRepository.findByEmail(customerDto.getEmail());
        if (existingCustomer.isPresent())
            throw new CustomerNotFoundException("Constants.EMAIL_ALREADY_EXISTS");

        Customer newCustomer = modelmapper.map(customerDto, Customer.class);
        customerRepository.save(newCustomer);
        return Constants.CUSTOMER_SUCCESSFULLY_REGISTERED;
    }

    @Override
    public CustomerDto customerLogin(LoginDto loginDto) {

        Optional<Customer> customer = customerRepository.findByEmail(loginDto.getEmail());
        if (customer.isEmpty())
            throw new CustomerNotFoundException(Constants.INVALID_LOGIN);

        if (!customer.get().getPassword().equals(loginDto.getPassword())) {
            throw new CustomerNotFoundException(Constants.INVALID_LOGIN);
        }

        return modelmapper.map(customer.get(), CustomerDto.class);
    }
}
