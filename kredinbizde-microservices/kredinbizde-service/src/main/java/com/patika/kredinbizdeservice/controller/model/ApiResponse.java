package com.patika.kredinbizdeservice.controller.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ApiResponse<T> {
    private T data;
    private boolean success;
    private String message;
    private LocalDate dateTime;
    private int statusCode;
}
