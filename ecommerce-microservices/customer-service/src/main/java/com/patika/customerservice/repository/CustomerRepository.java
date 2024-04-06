package com.patika.customerservice.repository;

import com.patika.customerservice.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CustomerRepository {
    private final AtomicLong idCounter = new AtomicLong();
    private final List<Customer> customers = new ArrayList<>();

    public Customer save(Customer customer) {
        customer.setId(idCounter.incrementAndGet());
        customers.add(customer);
        return customer;
    }


    public List<Customer> getAll() {
        return customers;
    }


    public Optional<Customer> findById(Long id) {

        return customers.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

}
