package com.js.crm.infrastructure.adapters.out.repository;

import com.js.crm.application.ports.out.CustomerRepositoryPort;
import com.js.crm.domain.model.Customer;
import com.js.crm.infrastructure.adapters.out.entity.CustomerEntity;
import com.js.crm.infrastructure.adapters.out.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class CustomerDBRepository implements CustomerRepositoryPort {

    private final CustomerJPARepository customerJPARepository;

    private final CustomerMapper customerMapper;

    @Override
    public Customer saveCustomer(Customer customer) {
        CustomerEntity customerEntity = customerMapper.toEntity(customer);
        customerJPARepository.save(customerEntity);
        return customerMapper.toCustomer(customerEntity);
    }

    @Override
    public Optional<Customer> getCustomerById(Long customerId) {
        Optional<CustomerEntity> customerEntity = customerJPARepository.findById(customerId);

        if(customerEntity.isEmpty()) {
            return Optional.empty();
        }

        Customer customer = customerMapper.toCustomer(customerEntity.get());
        return Optional.of(customer);
    }
}
