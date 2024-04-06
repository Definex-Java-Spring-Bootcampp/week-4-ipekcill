package com.patika.kredinbizdeservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
public abstract class Loan extends Auditable implements Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    private Integer installment;
    private Double interestRate;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "bank_id", referencedColumnName = "id")
    private Bank bank;

    @Override
    public String toString() {
        return "Loan{" +
                "amount=" + amount +
                ", installment=" + installment +
                ", bank=" + bank +
                ", interestRate=" + interestRate +
                '}';
    }
}
