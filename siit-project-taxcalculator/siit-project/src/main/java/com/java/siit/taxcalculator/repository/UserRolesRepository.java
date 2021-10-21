package com.java.siit.taxcalculator.repository;

import com.java.siit.taxcalculator.config.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRoles,String>{
    UserRoles getByEmail(String email);

}

