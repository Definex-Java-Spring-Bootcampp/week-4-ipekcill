package com.patika.stockservice.service;

import com.patika.stockservice.dto.StockDto;
import com.patika.stockservice.dto.StockUpdateDto;

public interface IStockService {
    StockDto saveStock(StockDto stockDto);

    void updateStock(StockUpdateDto stockUpdateDto);

    Boolean controlStock(StockUpdateDto request);
}
