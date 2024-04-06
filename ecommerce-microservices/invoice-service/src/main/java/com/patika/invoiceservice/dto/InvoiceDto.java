package com.patika.invoiceservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class InvoiceDto {
        private Long id;
        private LocalDateTime invoiceDate;
        private UUID orderNo;
        private Double amount;
    }

