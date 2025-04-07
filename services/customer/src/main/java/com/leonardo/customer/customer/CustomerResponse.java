package com.leonardo.customer.customer;


// utilizado para ser a resposta dos itens que serao enviados do data base
// pela api
public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email,
        Address address
) {
}
