package com.patika.kredinbizdeservice.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.patika.kredinbizdeservice.controller.model.ApiResponse;
import com.patika.kredinbizdeservice.producer.LogProducer;
import com.patika.kredinbizdeservice.producer.dto.ExceptionLogDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

    private final LogProducer logProducer;

    @Value("${spring.application.name}")
    private String source;

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ApiResponse<Void>> handleDefaultException(Throwable e) {
        ApiResponse<Void> apiResponse = ApiResponse.<Void>builder()
                .message(e.getMessage())
                .dateTime(LocalDate.now())
                .success(false)
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();

        ExceptionLogDto dto = createLogDto(e.getMessage());
        String jsonString = dtoMapper(dto);
        log.info("Log produced: {}", jsonString);
        logProducer.sendMessage(jsonString);
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(TechnicalException.class)
    public ResponseEntity<ApiResponse<Void>> handleTechnicalException(TechnicalException e) {
        ApiResponse<Void> apiResponse = ApiResponse.<Void>builder()
                .message(e.getMessage())
                .dateTime(LocalDate.now())
                .success(false)
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();

        ExceptionLogDto dto = createLogDto(e.getMessage());
        String jsonString = dtoMapper(dto);
        log.info("Log produced: {}", jsonString);
        logProducer.sendMessage(jsonString);
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
        ExceptionLogDto dto = createLogDto(e.getMessage());
        String jsonString = dtoMapper(dto);
        log.info("Log produced: {}", jsonString);
        logProducer.sendMessage(jsonString);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    private ExceptionLogDto createLogDto(String message) {
        return ExceptionLogDto.builder()
                .message(message)
                .exceptionDate(LocalDateTime.now())
                .source(source)
                .build();
    }

    private String dtoMapper(ExceptionLogDto dto) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonString = objectMapper.writeValueAsString(dto);
            log.info("Log produced: {}", jsonString);
            return jsonString;
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
