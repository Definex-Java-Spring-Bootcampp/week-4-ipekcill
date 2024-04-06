package com.patika.orderservice.producer.dto;

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
