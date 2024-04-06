package com.patika.orderservice.producer;

import com.patika.orderservice.configuration.RabbitMQConfiguration;
import com.patika.orderservice.producer.dto.NotificationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {


    private final RabbitMQConfiguration rabbitMQConfiguration;
    private final AmqpTemplate amqpTemplate;

    public void sendNotification(NotificationDto notificationDTO) {
        log.info("notification sent: {}", notificationDTO);
        amqpTemplate.convertSendAndReceive(rabbitMQConfiguration.getExchange(), rabbitMQConfiguration.getRoutingkey(), notificationDTO);
    }

}
