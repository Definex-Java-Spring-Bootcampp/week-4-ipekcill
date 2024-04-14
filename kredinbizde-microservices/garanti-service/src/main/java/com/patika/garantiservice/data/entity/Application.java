package com.patika.garantiservice.data.entity;

import com.patika.garantiservice.enums.ApplicationStatus;
import com.patika.garantiservice.enums.LoanType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Application {
    private Long id;
    private LocalDateTime createDate;
    private ApplicationStatus applicationStatus;
    private Long productId;
    private Long userId;
    private LoanType productLoanType;
}
