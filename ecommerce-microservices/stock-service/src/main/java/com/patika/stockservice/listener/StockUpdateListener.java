package com.patika.stockservice.listener;

import com.patika.stockservice.dto.StockUpdateDto;
import com.patika.stockservice.service.IStockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class StockUpdateListener {

    private final IStockService stockService;

    @RabbitListener(queues = "${rabbitmq.stock.queue}")
    public void sendNotification(StockUpdateDto stockUpdateDto) {
        log.info("kuyruktan okudun: {}", stockUpdateDto);
        stockService.updateStock(stockUpdateDto);
    }
}