package com.java.siit.taxcalculator.domain.model.business;


import com.java.siit.taxcalculator.domain.entity.business.PfaEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PfaDTO {

    private long id;
    private long cui;
    private int income;



    private int CASS;
    private int CAS;
    private long incomeTaxes;
    private long incomeTaxesPerMonth;
    private long dividendTaxesPerMonth;
    private long taxesTotal;
    private long fiscalYear;
    private long loginId;
    private long totalTaxesById;
    private long totalTaxesByFiscalYear;


}
