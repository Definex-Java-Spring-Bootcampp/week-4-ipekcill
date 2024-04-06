package com.patika.stockservice.service;

import com.patika.stockservice.dto.StockDto;
import com.patika.stockservice.dto.StockUpdateDto;
import com.patika.stockservice.exceptions.BusinessException;
import com.patika.stockservice.repository.StockRepository;
import com.patika.stockservice.mapper.DtoConverter;
import com.patika.stockservice.model.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService implements IStockService {
    private final StockRepository stockRepository;
    private final DtoConverter dtoConverter;

    @Override
    public StockDto saveStock(StockDto stockDto) {
        Stock stock = dtoConverter.toStock(stockDto);
        return dtoConverter.toStockDto(stockRepository.save(stock));
    }

    @Override
    public void updateStock(StockUpdateDto stockUpdateDto) {
        stockUpdateDto.getProductStockMap().forEach((skuCode, value) -> {
            Stock stock = stockRepository.findBySkuCode(skuCode)
                    .orElseThrow(() -> new BusinessException("Stock not found by given skuCode"));
            if (stock.getQuantity() < value) {
                throw new BusinessException("Stock problem");
            }
            Integer quantity = stock.getQuantity() - value;
            stock.setQuantity(quantity);
            stockRepository.save(stock);
        });
    }

    @Override
    public Boolean controlStock(StockUpdateDto request) {
        return request.getProductStockMap().entrySet().stream()
                .allMatch(entry -> {
                    String skuCode = entry.getKey();
                    Integer value = entry.getValue();
                    Stock stock = stockRepository.findBySkuCode(skuCode)
                            .orElseThrow(() -> new BusinessException("Stock not found by given skuCode"));
                    return value <= stock.getQuantity();
                });
    }
}
