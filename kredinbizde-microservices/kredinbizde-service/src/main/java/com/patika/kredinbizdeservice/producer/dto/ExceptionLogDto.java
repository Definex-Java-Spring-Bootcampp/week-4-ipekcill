package com.patika.kredinbizdeservice.producer.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ExceptionLogDto {
    private String message;
    private String source;
    private LocalDateTime exceptionDate;
}
