package com.patika.garantiservice.dto;

import com.patika.garantiservice.enums.LoanType;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationRequestDto {
    private Long userId;
    private Long productId;
    private LoanType productLoanType;
}
