package com.patika.notificationservice.strategy;


import com.patika.notificationservice.strategy.dto.NotificationDto;
import com.patika.notificationservice.strategy.dto.enums.NotificationType;

public interface NotificationStrategy {
    void sendMessage(NotificationDto notification);

    NotificationType notificationType();
}
