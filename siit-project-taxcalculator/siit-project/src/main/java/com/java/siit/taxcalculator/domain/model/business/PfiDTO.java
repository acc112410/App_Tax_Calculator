package com.java.siit.taxcalculator.domain.model.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PfiDTO {
    private long id;
    private int income;
    private long cui;
    private String name;
    private int CASS;
    private int CAS;
    private long incomeTaxes;
    private long incomeTaxesPerMonth;
    private long dividendTaxesPerMonth;
}
