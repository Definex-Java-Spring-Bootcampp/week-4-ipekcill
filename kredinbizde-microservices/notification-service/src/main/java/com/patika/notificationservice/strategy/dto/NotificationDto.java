package com.patika.notificationservice.strategy.dto;

import com.patika.notificationservice.strategy.dto.enums.NotificationType;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class NotificationDto {

    private NotificationType notificationType;
    private String message;
    private String to;
}
