package com.patika.stockservice.dto;

import lombok.*;

import java.util.Map;
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class StockUpdateDto {
    private Map<String, Integer> productStockMap;
}
