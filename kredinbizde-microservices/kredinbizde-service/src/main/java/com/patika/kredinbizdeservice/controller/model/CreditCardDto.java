package com.patika.kredinbizdeservice.controller.model;

import com.patika.kredinbizdeservice.enums.LoanType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class CreditCardDto {
    private Long id;
    private String name;
    private BigDecimal fee;
    private BigDecimal limit;
    private BankDto bank;
    private LoanType loanType;
    private List<CampaignDto> campaigns;
}
