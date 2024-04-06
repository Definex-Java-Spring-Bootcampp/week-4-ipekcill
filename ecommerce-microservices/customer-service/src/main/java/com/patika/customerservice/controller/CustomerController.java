package com.patika.customerservice.controller;

import com.patika.customerservice.controller.model.ApiResponse;
import com.patika.customerservice.dto.CustomerDto;
import com.patika.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/customer")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public CustomerDto createCustomer(@RequestBody CustomerDto request) {
        return customerService.createCustomer(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> getUserMailById(@PathVariable Long id) {
        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .data(customerService.getUserMailById(id))
                .message("Customer info retrieved")
                .dateTime(LocalDate.now())
                .success(true)
                .statusCode(HttpStatus.CREATED.value())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
