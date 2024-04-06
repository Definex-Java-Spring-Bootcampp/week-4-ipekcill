package com.patika.orderservice.producer.dto;

import com.patika.orderservice.producer.enums.NotificationType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationDto {
    private String to;
    private NotificationType notificationType;
    private String message;
}
