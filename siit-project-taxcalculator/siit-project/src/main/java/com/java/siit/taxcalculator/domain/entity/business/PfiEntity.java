package com.java.siit.taxcalculator.domain.entity.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "PFI")
public class PfiEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "income")
    private int income;

    @Column(name = "cui")
    private long cui;

    @Column(name = "name")
    private String name;

    @Email(message = "Email should be valid")
    @Column(name = "email")
    private String email;

    @Column(name = "CASS")
    private int CASS;

    @Column(name = "CAS")
    private int CAS;

    @Column(name = "incomeTaxes")
    private long incomeTaxes;

    @Column(name = "incomeTaxesPerMonth")
    private long incomeTaxesPerMonth;

    @Column(name = "dividendTaxesPerMonth")
    private long dividendTaxesPerMonth;
}
