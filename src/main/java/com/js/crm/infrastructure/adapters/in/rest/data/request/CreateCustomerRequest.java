package com.js.crm.infrastructure.adapters.in.rest.data.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerRequest {

    @NotEmpty(message = "Name must not be empty")
    private String name;
}
