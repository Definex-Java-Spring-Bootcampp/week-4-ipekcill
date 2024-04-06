package com.patika.customerservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phoneNumber;
}
