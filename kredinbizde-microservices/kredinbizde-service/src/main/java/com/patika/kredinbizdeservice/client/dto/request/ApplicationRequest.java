package com.patika.kredinbizdeservice.client.dto.request;

import com.patika.kredinbizdeservice.enums.LoanType;
import lombok.*;
@Data
@Builder
public class ApplicationRequest {
    private Long userId;
    private Long productId;
    private LoanType productType;
}
