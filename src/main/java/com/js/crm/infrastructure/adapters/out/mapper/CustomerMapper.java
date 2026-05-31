package com.js.crm.infrastructure.adapters.out.mapper;

import com.js.crm.domain.model.Customer;
import com.js.crm.infrastructure.adapters.out.entity.CustomerEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomerMapper {

    @Autowired
    private ModelMapper mapper;

    public Customer toCustomer(CustomerEntity entity) {
        return mapper.map(entity, Customer.class);
    }

    public CustomerEntity toEntity(Customer customer) {
        return mapper.map(customer, CustomerEntity.class);
    }
}
