package com.patika.orderservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemDto {
    private String skuCode;
    private Integer quantity;
    private Double itemPrice;
}
