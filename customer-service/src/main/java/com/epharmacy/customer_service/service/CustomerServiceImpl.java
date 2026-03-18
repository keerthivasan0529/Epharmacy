package com.epharmacy.customer_service.service;

import com.epharmacy.customer_service.dto.UpdateProfileDto;
import com.epharmacy.customer_service.utility.Constants;
import org.modelmapper.ModelMapper;
import com.epharmacy.customer_service.dto.CustomerDto;
import com.epharmacy.customer_service.dto.LoginDto;
import com.epharmacy.customer_service.entity.Customer;
import com.epharmacy.customer_service.repository.CustomerRepository;
import com.epharmacy.customer_service.exception.CustomerNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
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
            throw new CustomerNotFoundException(Constants.EMAIL_ALREADY_EXISTS);

        LocalDate today = LocalDate.now();
        int age = Period.between(customerDto.getDob(), today).getYears();
        if (age < 18)
            throw new CustomerNotFoundException(Constants.AGE_RESTRICTION);

        Customer newCustomer = modelmapper.map(customerDto, Customer.class);
//        for (int i = 0; i < newCustomer.getAddressList().size(); i++) {
//            newCustomer.getAddressList().get(i).setCustomer(newCustomer);
//        }
        if (newCustomer.getAddressList() != null) {
            for (int i = 0; i < newCustomer.getAddressList().size(); i++) {
                newCustomer.getAddressList().get(i).setCustomer(newCustomer);
            }
        }
        customerRepository.save(newCustomer);
        return Constants.CUSTOMER_SUCCESSFULLY_REGISTERED;
    }

    @Override
    public CustomerDto customerLogin(LoginDto loginDto) {

        Optional<Customer> customer = customerRepository.findByEmail(loginDto.getEmail());
        if (customer.isEmpty())
            throw new CustomerNotFoundException(Constants.INVALID_LOGIN);

        Customer existingCustomer = customer.get();
        if (!existingCustomer.getPassword()
                .equals(loginDto.getPassword()))
            throw new CustomerNotFoundException(Constants.INVALID_LOGIN);

        CustomerDto customerDto = modelmapper.map(existingCustomer, CustomerDto.class);

        return customerDto;
    }

    @Override
    public CustomerDto viewProfile(Integer customerId) {

//    After Login:
//    → Customer is logged in
//    → Frontend stores customerId
//    → When viewing profile
//      frontend sends customerId NOT email!

        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isEmpty()) {
            throw new CustomerNotFoundException(Constants.CUSTOMER_NOT_FOUND);
        }
            Customer existingCustomer = customer.get();
            CustomerDto customerDto = modelmapper.map(existingCustomer, CustomerDto.class);
            return customerDto;
    }

    @Override
    public CustomerDto updateProfile(UpdateProfileDto updateProfileDto) {

        Optional<Customer> customer = customerRepository.findById(updateProfileDto.getCustomerId());
        if (customer.isEmpty()) {
            throw new CustomerNotFoundException(Constants.CUSTOMER_NOT_FOUND);
        }
            Customer existingCustomer = customer.get();
            existingCustomer.setName(updateProfileDto.getName());
            existingCustomer.setEmail(updateProfileDto.getEmail());
            existingCustomer.setContactNumber(updateProfileDto.getContactNumber());
            customerRepository.save(existingCustomer);

        CustomerDto updatedCustomerDto = modelmapper.map(existingCustomer,CustomerDto.class);
        return updatedCustomerDto;
    }

//    @Override
//    public String upgradeToPrime(Integer customerId, Integer planId) {
//
//        Optional<Customer> customer = customerRepository.findById(customerId);
//        if (customer.isEmpty()) {
//            throw new CustomerNotFoundException(Constants.CUSTOMER_NOT_FOUND);
//        }
//        Customer existingCustomer = customer.get();
//        existingCustomer.setPlan(Constants.PRIME);
//        if(planId == 1) {
//            existingCustomer.setPlanExpiryDate(LocalDate.now().plusMonths(1));
//        }
//        else if (planId == 2) {
//            existingCustomer.setPlanExpiryDate(LocalDate.now().plusMonths(3));
//        }
//        else if (planId == 3) {
//            existingCustomer.setPlanExpiryDate(LocalDate.now().plusYears(1));
//        }
//        else {
//            throw new CustomerNotFoundException(Constants.INVALID_PLAN);
//        }
//        customerRepository.save(existingCustomer);
//
//        return Constants.PRIME_UPGRADED;
//    }

}
