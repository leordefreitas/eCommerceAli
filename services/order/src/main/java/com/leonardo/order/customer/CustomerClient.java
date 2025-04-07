package com.leonardo.order.customer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

// isso e feito para ter acesso ao microservice de outro database
@FeignClient(
        name = "customer-service",
        url = "${application.config.customer-url}" // isso esta no configurations/customer-service.yml
)
public interface CustomerClient {

    // eu pego assim la no customer controller
    @GetMapping("/{customer-id}")
    Optional<CustomerResponse> findCustomerById(@PathVariable("customer-id") String customerId);
}
