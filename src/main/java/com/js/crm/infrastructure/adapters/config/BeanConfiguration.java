package com.js.crm.infrastructure.adapters.config;

import com.js.crm.application.ports.out.CustomerRepositoryPort;
import com.js.crm.domain.service.CustomerService;
import com.js.crm.infrastructure.adapters.out.mapper.CustomerMapper;
import com.js.crm.infrastructure.adapters.out.repository.CustomerDBRepository;
import com.js.crm.infrastructure.adapters.out.repository.CustomerJPARepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public CustomerMapper customerMapper() {
        return new CustomerMapper();
    }

    @Bean
    public CustomerDBRepository customerDBRepository(CustomerJPARepository customerJPARepository, CustomerMapper customerMapper) {
        return new CustomerDBRepository(customerJPARepository, customerMapper);
    }

    @Bean
    public CustomerService CustomerService(CustomerRepositoryPort customerRepository) {
        return new CustomerService(customerRepository);
    }
}
