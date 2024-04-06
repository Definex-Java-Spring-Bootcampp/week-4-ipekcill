package com.patika.garantiservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationRequestDto {
    private Long userId;
    private Long productId;
}
