package com.patika.kredinbizdeservice.client.dto.response;

import com.patika.kredinbizdeservice.enums.LoanType;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
public class ApplicationResponse {
    private Long id;
    private LocalDateTime createDate;
    private ApplicationStatus applicationStatus;
    private Long productId;
    private Long userId;
    private LoanType productType;
}
