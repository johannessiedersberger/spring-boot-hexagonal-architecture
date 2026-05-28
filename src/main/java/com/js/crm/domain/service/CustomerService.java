package com.js.crm.domain.service;

import com.js.crm.application.ports.in.CustomerUseCase;
import com.js.crm.application.ports.out.CustomerRepositoryAdapter;
import com.js.crm.domain.exception.CustomerNotFoundException;
import com.js.crm.domain.model.Customer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomerService implements CustomerUseCase {

    private final CustomerRepositoryAdapter customerRepository;


    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.saveCustomer(customer);
    }

    @Override
    public Customer getCustomerById(Long customerId) {
        return customerRepository.getCustomerById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("No customer with id: " + customerId));
    }
}
