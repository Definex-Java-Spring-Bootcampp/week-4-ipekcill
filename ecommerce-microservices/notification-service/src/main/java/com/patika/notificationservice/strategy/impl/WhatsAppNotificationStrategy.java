package com.patika.notificationservice.strategy.impl;

import com.patika.notificationservice.strategy.NotificationStrategy;
import com.patika.notificationservice.strategy.dto.NotificationDto;
import com.patika.notificationservice.strategy.dto.enums.NotificationType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WhatsAppNotificationStrategy implements NotificationStrategy {
    @Override
    public void sendMessage(NotificationDto notification) {
        log.info(notification.getMessage()+" send to " + notification.getTo());
    }

    @Override
    public NotificationType notificationType() {
        return NotificationType.WHATSAPP;
    }
}



