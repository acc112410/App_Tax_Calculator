package com.java.siit.taxcalculator.mapper;

import com.java.siit.taxcalculator.domain.entity.business.PfaEntity;
import com.java.siit.taxcalculator.domain.entity.business.SrlEntity;
import com.java.siit.taxcalculator.domain.model.business.PfaDTO;
import com.java.siit.taxcalculator.domain.model.business.SrlDTO;
import org.springframework.core.convert.converter.Converter;

public class SrlEntityToSrlDto implements Converter<SrlEntity, SrlDTO> { @Override
public SrlDTO convert(SrlEntity srlEntity) {
    return SrlDTO.builder()
            .id(srlEntity.getId())
            .CAS(srlEntity.getCAS())
            .CASS(srlEntity.getCASS())
            .cui(srlEntity.getCui())
            .dividendTaxesPerMonth(srlEntity.getDividendTaxesPerMonth())
            .income(srlEntity.getIncome())
            .incomeTaxes(srlEntity.getIncomeTaxes())
            .incomeTaxesPerMonth(srlEntity.getIncomeTaxesPerMonth())
            .fiscalYear(srlEntity.getFiscalYear())
            .build();
}
}
