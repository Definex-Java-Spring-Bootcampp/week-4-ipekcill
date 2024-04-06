package com.patika.notificationservice.listener;


import com.patika.notificationservice.strategy.NotificationContext;
import com.patika.notificationservice.strategy.dto.NotificationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationListener {
    private final NotificationContext notificationContext;

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void sendNotification(NotificationDto notificationDto) {
        log.info("kuyruktan okudun: {}", notificationDto);
        notificationContext.sendMessage(notificationDto);
    }
}