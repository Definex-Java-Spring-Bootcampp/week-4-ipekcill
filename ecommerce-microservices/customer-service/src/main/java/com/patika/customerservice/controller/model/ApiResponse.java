package com.patika.customerservice.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private T data;
    private boolean success;
    private String message;
    private LocalDate dateTime;
    private int statusCode;
}
