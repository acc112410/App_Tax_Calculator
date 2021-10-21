package com.java.siit.taxcalculator.repository.business;

import com.java.siit.taxcalculator.domain.entity.business.PfiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PfiRepository extends JpaRepository<PfiEntity, Long> {
}
