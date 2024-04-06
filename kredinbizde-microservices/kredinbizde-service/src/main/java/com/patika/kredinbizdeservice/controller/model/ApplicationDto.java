package com.patika.kredinbizdeservice.controller.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApplicationDto {
    private Long id;
    private LocalDateTime localDateTime;
    private ProductDto product;
    private UserDto user;
}
