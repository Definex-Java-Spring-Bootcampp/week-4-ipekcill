package com.patika.kredinbizdeservice.model;

import com.patika.kredinbizdeservice.enums.LoanType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "credit_cards")
public class CreditCard extends Auditable implements Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "fee")
    private BigDecimal fee;
    @Column(name = "credit_limit")
    private BigDecimal limit;
    @OneToMany(mappedBy = "creditCard", cascade = CascadeType.DETACH)
    private List<Campaign> campaigns;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "bank_id", referencedColumnName = "id")
    private Bank bank;
    @Column(name = "loan_type")
    @Enumerated(EnumType.STRING)
    private final LoanType loanType = LoanType.CREDIT_CARD;

    @Override
    public String toString() {
        return "CreditCard{" +
                "fee=" + fee +
                ", limit=" + limit +
                ", campaigns=" + campaigns +
                ", loanType=" + loanType +
                '}';
    }
}
