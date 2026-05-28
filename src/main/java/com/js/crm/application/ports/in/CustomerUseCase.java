package com.js.crm.application.ports.in;

import com.js.crm.domain.model.Customer;

public interface CustomerUseCase {

    Customer createCustomer(Customer customer);

    Customer getCustomerById(Long customerId);
}
