package com.patika.orderservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class OrderDto {
    private LocalDateTime createdDate;
    private Long customerId;
    private List<OrderItemDto> orderItems;
    private Double totalPrice;
    private UUID orderNo;
}
