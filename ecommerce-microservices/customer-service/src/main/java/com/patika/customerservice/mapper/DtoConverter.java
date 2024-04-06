package com.patika.customerservice.mapper;

import com.patika.customerservice.dto.CustomerDto;
import com.patika.customerservice.model.Customer;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class DtoConverter {
    public CustomerDto toCustomerDto(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .email(customer.getEmail())
                .name(customer.getName())
                .phoneNumber(customer.getPhoneNumber())
                .surname(customer.getSurname())
                .build();
    }

    public Customer toCustomer(CustomerDto customerDto) {
        return Customer.builder()
                .id(customerDto.getId())
                .email(customerDto.getEmail())
                .name(customerDto.getName())
                .phoneNumber(customerDto.getPhoneNumber())
                .surname(customerDto.getSurname())
                .password(hashPassword(customerDto.getPassword()))
                .build();
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
