package com.patika.stockservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockDto {
    private Integer quantity;
    private String skuCode;
}
