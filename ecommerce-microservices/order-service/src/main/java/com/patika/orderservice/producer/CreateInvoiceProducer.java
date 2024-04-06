package com.patika.orderservice.producer;

import com.patika.orderservice.configuration.RabbitMQConfiguration;
import com.patika.orderservice.producer.dto.CreateInvoiceDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateInvoiceProducer {
    private final RabbitMQConfiguration rabbitMQConfiguration;
    private final AmqpTemplate amqpTemplate;

    public void sendCreateInvoice(CreateInvoiceDto createInvoiceDto) {
        log.info("create invoice sent: {}", createInvoiceDto);
        amqpTemplate.convertSendAndReceive(rabbitMQConfiguration.getExchange(), rabbitMQConfiguration.getInvoiceRoutingkey(), createInvoiceDto);
    }
}
