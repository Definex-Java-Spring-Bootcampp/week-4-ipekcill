package com.patika.customerservice.service;

import com.patika.customerservice.dto.CustomerDto;

public interface ICustomerService {
    CustomerDto createCustomer(CustomerDto customerDto);

    String getUserMailById(Long id);
}
