package com.patika.kredinbizdeservice.controller.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BankDto {
    private Long id;
    private String name;
}
