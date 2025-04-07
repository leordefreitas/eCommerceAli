package com.leonardo.customer.customer;

import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    private final CustomerRepository customerRepository;

    public CustomerMapper(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // aqui ele pega do json e joga para o java
    public Customer toCustomer(CustomerRequest request) {
        if(request==null) {
            return null;
        }
        return Customer.builder()
                .id(request.id())
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .address(request.address())
                .build();
    }

    // aqui ele chama a classe customer response e coloca exatamente o que ele
    // quer para que apareca no api
    public CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
