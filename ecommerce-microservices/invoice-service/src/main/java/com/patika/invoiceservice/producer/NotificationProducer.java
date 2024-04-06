package com.patika.invoiceservice.producer;

import com.patika.invoiceservice.configuration.RabbitMQConfigurationForProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {

    // private final RabbitTemplate rabbitTemplate;

    private final RabbitMQConfigurationForProducer rabbitMQConfigurationForProducer;
    private final AmqpTemplate amqpTemplate;

    public void sendNotification( ) {
        log.info("notification sent");
        amqpTemplate.convertSendAndReceive(rabbitMQConfigurationForProducer.getExchange(), rabbitMQConfigurationForProducer.getInvoiceNotificationRoutingkey());
    }

}
