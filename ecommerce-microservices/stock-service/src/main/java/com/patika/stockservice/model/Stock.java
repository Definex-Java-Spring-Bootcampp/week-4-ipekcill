package com.patika.stockservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Stock {
    private Long id;
    private Integer quantity;
    private String skuCode;
}
