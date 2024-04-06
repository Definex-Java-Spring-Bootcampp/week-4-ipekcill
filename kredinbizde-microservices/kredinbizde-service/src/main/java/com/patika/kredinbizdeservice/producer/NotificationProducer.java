package com.patika.kredinbizdeservice.producer;

import com.patika.kredinbizdeservice.configuration.RabbitMQConfig;
import com.patika.kredinbizdeservice.producer.dto.NotificationDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {

    // private final RabbitTemplate rabbitTemplate;

    private final RabbitMQConfig rabbitMQConfig;
    private final AmqpTemplate amqpTemplate;

    public void sendNotification(NotificationDTO notificationDTO) {
        log.info("notification sent: {}", notificationDTO);
        amqpTemplate.convertSendAndReceive(rabbitMQConfig.getExchange(), rabbitMQConfig.getRoutingkey(), notificationDTO);
    }

}
