package com.patika.kredinbizdeservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "addresses")
public class Address extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "address_title")
    private String addressTitle;
    @Column(name = "address_description")
    private String addressDescription;
    @Column(name = "province")
    private String province;

}
