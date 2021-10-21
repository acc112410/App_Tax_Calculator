package com.java.siit.taxcalculator.mapper;

import com.java.siit.taxcalculator.domain.entity.LoginEntity;

import com.java.siit.taxcalculator.domain.model.LoginDTO;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;




    @Component
    @AllArgsConstructor
    public class LoginEntityToLoginDTOWithoutPfa implements Converter<LoginEntity, LoginDTO> {
        @Override
        public LoginDTO convert(LoginEntity loginEntity) {
            return LoginDTO.builder().email(loginEntity.getEmail())
                    .enabled(loginEntity.getEnabled())
                    .id(loginEntity.getId())
                    .password(loginEntity.getPassword())

                    .build();
        }


    }


