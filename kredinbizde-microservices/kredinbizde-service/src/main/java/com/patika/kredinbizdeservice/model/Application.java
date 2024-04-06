package com.patika.kredinbizdeservice.model;

import com.patika.kredinbizdeservice.enums.ApplicationStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "applications")
public class Application extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "local_date_time")
    private LocalDateTime localDateTime;
    @Column(name = "application_status")
    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;
    @ManyToOne
    @JoinColumn(name = "consumer_loan_id", referencedColumnName = "id")
    private ConsumerLoan consumerLoan;
    @ManyToOne
    @JoinColumn(name = "house_loan_id", referencedColumnName = "id")
    private HouseLoan houseLoan;
    @ManyToOne
    @JoinColumn(name = "vehicle_loan_id", referencedColumnName = "id")
    private VehicleLoan vehicleLoan;
    @ManyToOne
    @JoinColumn(name = "credit_card_id", referencedColumnName = "id")
    private CreditCard creditCard;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
