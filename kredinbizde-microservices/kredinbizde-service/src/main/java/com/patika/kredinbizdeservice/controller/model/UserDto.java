package com.patika.kredinbizdeservice.controller.model;

import com.patika.kredinbizdeservice.model.Address;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phoneNumber;
    private Boolean isActive;
    private Address address;
}
