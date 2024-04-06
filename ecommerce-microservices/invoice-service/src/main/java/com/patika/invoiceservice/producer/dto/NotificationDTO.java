package com.patika.invoiceservice.producer.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class  {

    private NotificationType notificationType;
    private String message;
    private String to;

}
