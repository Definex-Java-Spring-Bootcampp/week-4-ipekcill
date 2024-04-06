package com.patika.orderservice.producer;

import com.patika.orderservice.configuration.RabbitMQConfiguration;
import com.patika.orderservice.producer.dto.StockUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class StockUpdateProducer {
    private final RabbitMQConfiguration rabbitMQConfiguration;
    private final AmqpTemplate amqpTemplate;

    public void sendStockUpdate(StockUpdateDto stockUpdateDto) {
        log.info("stock update sent: {}", stockUpdateDto);
        amqpTemplate.convertSendAndReceive(rabbitMQConfiguration.getExchange(), rabbitMQConfiguration.getStockRoutingkey(), stockUpdateDto);
    }
}
