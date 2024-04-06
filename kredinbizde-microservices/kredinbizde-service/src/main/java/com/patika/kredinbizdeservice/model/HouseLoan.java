package com.patika.kredinbizdeservice.model;

import com.patika.kredinbizdeservice.enums.LoanType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "loans")
public class HouseLoan extends Loan {
    @Column(name = "title")
    private String title;
    @Column(name = "loan_type")
    @Enumerated(EnumType.STRING)
    private final LoanType loanType = LoanType.HOUSE_LOAN;
}
