package com.patika.garantiservice.controller.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiResponse<T> {
    private T data;
    private boolean success;
    private String message;
    private LocalDateTime dateTime;
}
