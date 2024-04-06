package com.patika.stockservice.repository;

import com.patika.stockservice.model.Stock;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class StockRepository {
    private List<Stock> stocks = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong();

    public Stock save(Stock stock) {
        stock.setId(idCounter.getAndIncrement());
        stocks.add(stock);
        return stock;
    }

    public List<Stock> getAll() {
        return stocks;
    }

    public Optional<Stock> findBySkuCode(String skuCode) {
        return stocks.stream().filter(f -> f.getSkuCode().equals(skuCode)).findFirst();
    }
}
