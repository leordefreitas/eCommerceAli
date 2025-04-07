package com.leonardo.customer.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        // o que foi passado aqui apenas para validar os valores adicionados
        // uma boa forma de implementa-los
        String id,
        @NotNull(message = "Customer firstname is required.")
        String firstname,
        @NotNull(message = "Customer lastname is required.")
        String lastname,
        @NotNull(message = "Customer email is required.")
        @Email(message = "Customer email is not a valid email address")
        String email,
        Address address
) {
}
