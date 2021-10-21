package com.java.siit.taxcalculator.repository;


import com.java.siit.taxcalculator.domain.entity.LoginEntity;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.java.siit.taxcalculator.config.UserRoles;
import com.java.siit.taxcalculator.domain.entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Long> {

    LoginEntity getByEmail(String email);
//   Optional<LoginEntity> selectLoginEntityByUsername(String email);

//     List <LoginEntity> getAllByUserRoles(String userRoles);

    @Query("SELECT l FROM LoginEntity l WHERE l.id = :id")
    LoginEntity findById( long id);


    @Query("SELECT l from LoginEntity l")
    List<LoginEntity> getAll();




}
