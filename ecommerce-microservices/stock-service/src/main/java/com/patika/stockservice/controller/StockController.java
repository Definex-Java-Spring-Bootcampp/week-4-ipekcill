package com.patika.stockservice.controller;

import com.patika.stockservice.dto.StockDto;
import com.patika.stockservice.dto.StockUpdateDto;
import com.patika.stockservice.service.IStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/stock")
public class StockController {
    private final IStockService stockService;

    @PostMapping
    public StockDto createStock(@RequestBody StockDto request) {
        return stockService.saveStock(request);
    }

    @PostMapping("/control")
    public Boolean stockControl(@RequestBody StockUpdateDto request) {
        return stockService.controlStock(request);
    }
}
