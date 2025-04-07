package com.leonardo.customer.customer;

import org.springframework.data.mongodb.repository.MongoRepository;

// passa aqui o mongo interface como o jpa, pois aqui quem lidara no back end
// sera o mongo
public interface CustomerRepository extends MongoRepository<Customer, String> {
}
