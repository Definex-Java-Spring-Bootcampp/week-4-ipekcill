package com.patika.stockservice.mapper;

import com.patika.stockservice.dto.StockDto;
import com.patika.stockservice.model.Stock;
import org.springframework.stereotype.Component;

@Component
public class DtoConverter {
    public Stock toStock(StockDto dto) {
        return Stock.builder()
                .skuCode(dto.getSkuCode())
                .quantity(dto.getQuantity())
                .build();
    }
    public StockDto toStockDto(Stock stock) {
        return StockDto.builder()
                .skuCode(stock.getSkuCode())
                .quantity(stock.getQuantity())
                .build();
    }
}
