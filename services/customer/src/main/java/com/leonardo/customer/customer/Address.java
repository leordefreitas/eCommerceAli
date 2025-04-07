package com.leonardo.customer.customer;

import lombok.*;
import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Validated // validacoes depois
public class Address {

    private String street;
    private String houseNumber;
    private String zipCode;
}
