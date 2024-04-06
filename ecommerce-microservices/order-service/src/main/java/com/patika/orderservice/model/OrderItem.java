package com.patika.orderservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItem {
    private String skuCode;
    private Integer quantity;
    private Double itemPrice;
}
