package com.js.crm.infrastructure.adapters.in.rest.data.request;

import jakarta.validation.constraints.NotEmpty;

public class UpdateCustomerRequest {

    @NotEmpty(message = "Id must not be empty")
    private Long id;

    @NotEmpty(message = "Name must not be empty")
    private String name;
}
