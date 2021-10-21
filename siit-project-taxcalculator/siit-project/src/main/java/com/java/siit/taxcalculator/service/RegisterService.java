package com.java.siit.taxcalculator.service;

import com.java.siit.taxcalculator.domain.entity.RegisterEntity;
import com.java.siit.taxcalculator.repository.RegisterRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final RegisterRepository registerRepository;

    public RegisterEntity create(RegisterEntity registerEntity) {
        registerEntity.setPassword(bCryptPasswordEncoder.encode(registerEntity.getPassword()));

        return registerRepository.save(registerEntity);
    }

}
