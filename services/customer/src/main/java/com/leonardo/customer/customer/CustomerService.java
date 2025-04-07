package com.leonardo.customer.customer;

import com.leonardo.customer.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;


    public String createCustomer(CustomerRequest request) {
        var customer = repository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        var customer = repository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                    String.format("Cannot update customer:: No customer found with the provided ID:: %s", request.id())
                ));
        mergerCustomer(customer, request);
        repository.save(customer);
    }

    // faz isso para que eu possa ter um melhor comportamento colocando os valores
    // corretos dentro de cada lugar e dando o merger, caso ja exista tal id
    private void mergerCustomer(Customer customer, CustomerRequest request) {

        if(StringUtils.isNotBlank(request.firstname())) {
            customer.setFirstname(request.firstname());
        }
        if(StringUtils.isNotBlank(request.lastname())) {
            customer.setLastname(request.lastname());
        }
        if(StringUtils.isNotBlank(request.email())) {
            customer.setEmail(request.email());
        }
        if((request.address() != null)) {
            customer.setAddress(request.address());
        }

    }

    public List<CustomerResponse> findAllCustomers() {
        return repository.findAll()
                .stream()
                .map(mapper::fromCustomer)
                .collect(Collectors.toList());
    }

    // isPresent ele retorna se exist alguma coisa para true se nao tem
    // nada e falso
    public Boolean existsById(String customerId) {
        return repository.findById(customerId)
                .isPresent();
    }

    public CustomerResponse findById(String customerId) {
        return repository.findById(customerId)
                .map(mapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(String.format(
                    "No customer found with the ID:: %s", customerId
                )));
    }

    public void deleteCustomer(String customerId) {
        repository.deleteById(customerId);
    }
}
