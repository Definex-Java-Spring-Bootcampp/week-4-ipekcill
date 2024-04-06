package com.patika.invoiceservice.producer.dto;

import com.patika.invoiceservice.producer.enums.NotificationType;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class NotificationDTO {

    private NotificationType notificationType;
    private String message;
    private String to;

}
