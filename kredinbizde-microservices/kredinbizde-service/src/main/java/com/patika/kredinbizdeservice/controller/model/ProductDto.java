package com.patika.kredinbizdeservice.controller.model;

import com.patika.kredinbizdeservice.enums.LoanType;
import com.patika.kredinbizdeservice.enums.VehicleStatusType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductDto {
    private Long id;
    private LoanType type;
    private String title;
    private BigDecimal amount;
    private Integer installment;
    private Double interestRate;
    private VehicleStatusType vehicleStatusType;
    private BankDto bank;
}