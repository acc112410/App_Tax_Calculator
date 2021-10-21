package com.java.siit.taxcalculator.mapper;

import com.java.siit.taxcalculator.domain.entity.business.PfaEntity;
import com.java.siit.taxcalculator.domain.model.business.PfaDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PfaEntityToPfaDto implements Converter<PfaEntity, PfaDTO> {
    @Override
    public PfaDTO convert(PfaEntity pfaEntity) {
        return PfaDTO.builder()
                .id(pfaEntity.getId())
                .CAS(pfaEntity.getCAS())
                .CASS(pfaEntity.getCASS())
                .cui(pfaEntity.getCui())
                .dividendTaxesPerMonth(pfaEntity.getDividendTaxesPerMonth())
                .income(pfaEntity.getIncome())
                .incomeTaxes(pfaEntity.getIncomeTaxes())
                .incomeTaxesPerMonth(pfaEntity.getIncomeTaxesPerMonth())
                .fiscalYear(pfaEntity.getFiscalYear())
                .build();
    }
}
