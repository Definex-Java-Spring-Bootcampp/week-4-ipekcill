package com.patika.kredinbizdeservice.model;

import com.patika.kredinbizdeservice.enums.SectorType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "campaigns")
public class Campaign extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private LocalDate dueDate;
    private LocalDate startingDate;
    private SectorType sectorType;

    @ManyToOne
    @JoinColumn(name = "credit_card_id")
    private CreditCard creditCard;
}
