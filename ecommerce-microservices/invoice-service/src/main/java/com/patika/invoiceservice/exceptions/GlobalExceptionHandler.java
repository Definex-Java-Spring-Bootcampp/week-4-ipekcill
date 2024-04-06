package com.patika.invoiceservice.exceptions;

import com.patika.invoiceservice.controller.model.ApiResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ApiResponse<Void>> handleDefaultException(Throwable e) {
        ApiResponse<Void> apiResponse = ApiResponse.<Void>builder()
                .message(e.getMessage())
                .dateTime(LocalDate.now())
                .success(false)
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusinessException(BusinessException e) {
        ApiResponse<Void> apiResponse = ApiResponse.<Void>builder()
                .message(e.getMessage())
                .dateTime(LocalDate.now())
                .success(false)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
