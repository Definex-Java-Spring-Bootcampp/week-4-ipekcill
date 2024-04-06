package com.patika.garantiservice.exceptions;

import com.patika.garantiservice.controller.model.ApiResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ApiResponse<Void>> handleDefaultException(Throwable e) {
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .data(null)
                .success(false)
                .message(e.getMessage())
                .dateTime(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TechnicalException.class)
    public ResponseEntity<ApiResponse<Void>> handleTechnicalException(TechnicalException e) {
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .data(null)
                .success(false)
                .message(e.getMessage())
                .dateTime(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusinessException(BusinessException e) {
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .data(null)
                .success(false)
                .message(e.getMessage())
                .dateTime(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
