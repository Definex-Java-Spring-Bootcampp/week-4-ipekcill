package com.patika.garantiservice.dto;

import com.patika.garantiservice.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationResponseDto {
    private LocalDateTime createDate;
    private ApplicationStatus applicationStatus;
    private Long productId;
    private Long userId;
}
