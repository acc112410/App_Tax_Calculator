package com.java.siit.taxcalculator.domain.model.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SrlDTO {
    private long id;
    private long cui;
    private int income;
    private int CASS;
    private int CAS;
    private long incomeTaxes;
    private long incomeTaxesPerMonth;
    private long dividendTaxesPerMonth;
    private long fiscalYear;
    private long loginId;
    private long totalTaxesById;
    private long totalTaxesByFiscalYear;
    private long taxesTotal;
}
