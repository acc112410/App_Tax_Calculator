package com.java.siit.taxcalculator.domain.Mappers;

import com.java.siit.taxcalculator.domain.entity.business.PfaEntity;
import com.java.siit.taxcalculator.domain.model.business.PfaDTO;

public class PfaMapper {

//    public PfaDTO toDto(PfaEntity pfaEntity) {
//        PfaDTO pfaDTO = new PfaDTO();
//
//        pfaDTO.setCAS(pfaEntity.getCAS());
//        pfaDTO.setCASS(pfaEntity.getCASS());
//        pfaDTO.setCui(pfaEntity.getCui());
//        pfaDTO.setFiscalYear(pfaEntity.getFiscalYear());
//        pfaDTO.setId(pfaEntity.getId());
//        pfaDTO.setIncome(pfaEntity.getIncome());
//        pfaDTO.setDividendTaxesPerMonth(pfaEntity.getDividendTaxesPerMonth());
//        pfaDTO.setIncomeTaxesPerMonth(pfaEntity.getIncomeTaxesPerMonth());
//        return pfaDTO;
//    }

    public PfaEntity toEntity(PfaDTO pfaDTO) {
        PfaEntity pfaEntity = new PfaEntity();

        pfaEntity.setCASS(pfaDTO.getCASS());
        pfaEntity.setCAS(pfaDTO.getCAS());
        pfaEntity.setCui(pfaDTO.getCui());
        pfaEntity.setFiscalYear(pfaDTO.getFiscalYear());
        pfaEntity.setId(pfaDTO.getId());
        pfaEntity.setIncome(pfaDTO.getIncome());
        pfaEntity.setDividendTaxesPerMonth(pfaDTO.getDividendTaxesPerMonth());
        pfaEntity.setIncomeTaxesPerMonth(pfaDTO.getIncomeTaxesPerMonth());
        return pfaEntity;
    }

}
