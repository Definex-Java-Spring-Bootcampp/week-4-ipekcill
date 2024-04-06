package com.patika.invoiceservice.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Builder
public class Invoice {
    private Long id;
    private LocalDateTime invoiceDate;
    private UUID orderNo;
    private Double amount;
    private UUID invoiceNo;
}
