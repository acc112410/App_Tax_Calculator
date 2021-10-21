package com.java.siit.taxcalculator.repository.business;

import com.java.siit.taxcalculator.domain.entity.business.PfaEntity;
import com.java.siit.taxcalculator.domain.entity.business.SrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SrlRepository extends JpaRepository<SrlEntity, Long> {
    List<SrlEntity> findAll();
    List<SrlEntity> getAllByLoginId(long id);
    List<SrlEntity> findAllByLoginId(long loginId);
    List<SrlEntity> findAllByFiscalYearAndLoginId(long fiscalYear, long loginId);
}
