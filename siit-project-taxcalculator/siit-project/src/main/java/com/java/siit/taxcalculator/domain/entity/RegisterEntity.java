package com.java.siit.taxcalculator.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "register")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Email
    @Column(name = "email")
    private String email;


    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "password")
    private String password;

    @Column(name = "typeOfBusiness")
    private TypeOfBusiness typeOfBusiness;

    @Column(name = "enabled")
    private String enabled;

}
