package com.patika.kredinbizdeservice.model;

import com.patika.kredinbizdeservice.enums.LoanType;
import com.patika.kredinbizdeservice.enums.VehicleStatusType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "loans")
public class VehicleLoan extends Loan {
    @Column(name = "title")
    private String title;
    @Column(name = "vehicle_status_type")
    @Enumerated(EnumType.STRING)
    private VehicleStatusType vehicleStatusType;
    @Column(name = "loan_type")
    @Enumerated(EnumType.STRING)
    private final LoanType loanType = LoanType.VEHICLE_LOAN;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private Bank bank;
}
