package com.java.siit.taxcalculator.domain.entity.business;


import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Table(name = "pfa")
public class PfaEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "income")
    private int income;

    @Column(name = "cui")
    private long cui;


//
//    @Column(name = "email")
//    private String email;


//    @Email(message = "Email should be valid")
//    @Column(name = "email")
//    private String email;



    @Column(name = "CASS")
    private int CASS;

    @Column(name = "CAS")
    private int CAS;

    @Column(name = "income_Taxes")
    private long incomeTaxes;

    @Column(name = "income_Taxes_Per_Month")
    private long incomeTaxesPerMonth;

    @Column(name = "dividend_taxes_Per_Month")
    private long dividendTaxesPerMonth;


    @Column(name = "fiscal_Year")
    private long fiscalYear;

    @Column(name = "login_Id")
    private long loginId;

    @Column(name="taxes_Total")
    private long taxesTotal;

    @Column(name = "total_Taxes_By_ID")
    private long totalTaxesById;

    @Column(name ="total_Fiscal_Year")
    private long totalTaxesByFiscalYear;

}
