package com.js.crm.application.ports.out;

import com.js.crm.domain.model.Customer;

import java.util.Optional;

public interface CustomerRepositoryPort {

    Customer saveCustomer(Customer customer);

    Customer udpateCustomer(Customer customer);

    Optional<Customer> getCustomerById(Long customerId);
}
