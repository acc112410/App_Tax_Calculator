package com.java.siit.taxcalculator.repository.business;

import com.java.siit.taxcalculator.domain.entity.LoginEntity;
import com.java.siit.taxcalculator.domain.entity.business.PfaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PfaRepository extends JpaRepository<PfaEntity, Long> {
    List<PfaEntity> findAll();
    List<PfaEntity> getAllByLoginId(long id);
    PfaEntity getPfaEntityById(int id);
    PfaEntity deletePfaEntityById(int id);
    List<PfaEntity> findAllByLoginId(long loginId);
    List<PfaEntity> findAllByFiscalYearAndLoginId(long fiscalYear, long loginId);

}
