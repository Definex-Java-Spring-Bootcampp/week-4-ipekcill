package com.patika.kredinbizdeservice.model;

import com.patika.kredinbizdeservice.enums.LoanType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "loans")
public class ConsumerLoan extends Loan {
    @Column(name = "title")
    private String title;
    @Column(name = "loan_type")
    @Enumerated(EnumType.STRING)
    private final LoanType loanType = LoanType.CONSUMER_LOAN;
}
