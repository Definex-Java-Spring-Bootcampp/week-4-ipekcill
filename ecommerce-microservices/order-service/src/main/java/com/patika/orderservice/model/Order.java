package com.patika.orderservice.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class Order {
    private Long id;
    private LocalDateTime createdDate;
    private Long customerId;
    private List<OrderItem> orderItems;
    private Double totalPrice;
    private UUID orderNo;
}
