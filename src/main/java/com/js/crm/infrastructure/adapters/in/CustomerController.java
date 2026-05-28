package com.js.crm.infrastructure.adapters.in;

import com.js.crm.application.ports.in.CustomerUseCase;
import com.js.crm.domain.model.Customer;
import com.js.crm.infrastructure.adapters.in.rest.data.request.CustomerRequest;
import com.js.crm.infrastructure.adapters.in.rest.data.response.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerUseCase customerUseCase;

    private final ModelMapper mapper;

    @PostMapping(value = "/customers")
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest customerToCreate) {
        Customer customer = mapper.map(customerToCreate, Customer.class);
        customer = customerUseCase.createCustomer(customer);

        return new ResponseEntity<>(mapper.map(customer, CustomerResponse.class), HttpStatus.CREATED);
    }

    @GetMapping(value = "/customers/{id}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable Long id) {
        Customer customer = customerUseCase.getCustomerById(id);
        return new ResponseEntity<>(mapper.map(customer, CustomerResponse.class), HttpStatus.OK);
    }

}
