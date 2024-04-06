package com.patika.notificationservice.strategy;

import com.patika.notificationservice.strategy.dto.NotificationDto;
import com.patika.notificationservice.strategy.dto.enums.NotificationType;
import com.patika.notificationservice.strategy.exception.NotFoundNotificationStrategy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@AllArgsConstructor
@Component
@Slf4j
public class NotificationContext {
    private final Map<NotificationType, NotificationStrategy> sendNotificationByType;

    public void sendMessage(NotificationDto notification) throws NotFoundNotificationStrategy {
        NotificationStrategy notificationStrategy = sendNotificationByType.getOrDefault(notification.getNotificationType(), null);
        if (Objects.isNull(notificationStrategy)) {
            throw new NotFoundNotificationStrategy("Notification Type not found. type: " + notification.getNotificationType());
        }
        notificationStrategy.sendMessage(notification);
    }
}
