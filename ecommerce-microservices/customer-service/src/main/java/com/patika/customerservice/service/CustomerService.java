package com.patika.customerservice.service;

import com.patika.customerservice.dto.CustomerDto;
import com.patika.customerservice.exceptions.BusinessException;
import com.patika.customerservice.mapper.DtoConverter;
import com.patika.customerservice.model.Customer;
import com.patika.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {
    private final CustomerRepository customerRepository;
    private final DtoConverter dtoConverter;

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = dtoConverter.toCustomer(customerDto);
        return dtoConverter.toCustomerDto(customerRepository.save(customer));
    }

    @Override
    public String getUserMailById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);

        return customer.map(Customer::getEmail).orElseThrow(() -> new BusinessException("User not found by given id"));
    }
}
