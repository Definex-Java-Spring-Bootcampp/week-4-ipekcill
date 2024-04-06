package com.patika.logservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "ExceptionLogs")
@Data
public class ExceptionLog {
    @Id
    private Integer id;
    private String message;
    private String source;
    private LocalDateTime exceptionDate;
}
