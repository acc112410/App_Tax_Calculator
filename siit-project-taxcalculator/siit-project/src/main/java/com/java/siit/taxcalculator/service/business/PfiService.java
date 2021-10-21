package com.java.siit.taxcalculator.service.business;



import com.java.siit.taxcalculator.domain.entity.business.PfaEntity;

import com.java.siit.taxcalculator.domain.entity.business.PfiEntity;
import com.java.siit.taxcalculator.repository.business.PfiRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PfiService {

    private final PfiRepository pfiRepository;


    public void createPfi(PfiEntity dto) {
        pfiRepository.save(PfiEntity.builder()
                .income(dto.getIncome())
                .CAS((dto.getIncome() * 25 / 100))
                .CASS((dto.getIncome() * 10 / 100))
                .incomeTaxes((dto.getIncome() * 10 / 100))
                .incomeTaxesPerMonth((dto.getIncome() * 0))
                .dividendTaxesPerMonth((dto.getIncome() * 0))
                .build());
    }

    public List<PfiEntity> findAll(PfiEntity pfiEntity) {
        return pfiRepository.findAll();
    }

    public void save(PfiEntity pfiEntity) {
        pfiRepository.save(pfiEntity);
    }

    public PfiEntity get(Long id) {
        return pfiRepository.findById(id).get();
    }

    public void delete(Long id) {
        pfiRepository.deleteById(id);
    }



}
