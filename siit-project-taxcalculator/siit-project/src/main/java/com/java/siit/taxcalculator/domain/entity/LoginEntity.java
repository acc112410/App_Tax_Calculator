package com.java.siit.taxcalculator.domain.entity;


import com.google.common.collect.Sets;
import com.java.siit.taxcalculator.config.UserRoles;
import com.java.siit.taxcalculator.domain.entity.business.PfaEntity;

import lombok.*;
import org.apache.catalina.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;

import java.util.Collection;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "login")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Email
    @Column(name = "user_email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_password")
    private String password;

    @Column(name = "type_of_business")
    private String typeOfBusiness;

    @Column(name = "enabled")
    private boolean enabled;

    public boolean getEnabled() {
        return enabled;
    }
}




