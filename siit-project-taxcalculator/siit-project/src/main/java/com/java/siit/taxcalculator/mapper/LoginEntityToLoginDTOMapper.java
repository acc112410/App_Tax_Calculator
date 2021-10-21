package com.java.siit.taxcalculator.mapper;

import com.java.siit.taxcalculator.domain.entity.LoginEntity;
import com.java.siit.taxcalculator.domain.entity.business.PfaEntity;
import com.java.siit.taxcalculator.domain.model.LoginDTO;
import com.java.siit.taxcalculator.domain.model.business.PfaDTO;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class LoginEntityToLoginDTOMapper implements Converter<LoginEntity, LoginDTO> {
    private final PfaEntityToPfaDto pfaEntityToPfaDto;
    @Override
    public LoginDTO convert(LoginEntity loginEntity) {
        return LoginDTO.builder().email(loginEntity.getEmail())
                .enabled(loginEntity.getEnabled())
                .id(loginEntity.getId())
                .password(loginEntity.getPassword())


        .build();
    }

    private List<PfaDTO> mapPfa(List<PfaEntity> pfaEntities) {
        return pfaEntities.stream()
                .map(pfaEntityToPfaDto::convert)
                .collect(Collectors.toList());
    }
}
