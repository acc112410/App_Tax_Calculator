package com.java.siit.taxcalculator.service;


import com.java.siit.taxcalculator.domain.entity.LoginEntity;
import com.java.siit.taxcalculator.repository.LoginRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import com.java.siit.taxcalculator.config.UserRoles;
import com.java.siit.taxcalculator.domain.entity.LoginEntity;
import com.java.siit.taxcalculator.domain.model.LoginDTO;
import com.java.siit.taxcalculator.mapper.LoginDTOToLoginEntity;
import com.java.siit.taxcalculator.mapper.LoginEntityToLoginDTOMapper;
import com.java.siit.taxcalculator.mapper.LoginEntityToLoginDTOWithoutPfa;
import com.java.siit.taxcalculator.repository.LoginRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class LoginService  {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private final LoginRepository loginRepository;


    private final LoginDTOToLoginEntity loginDTOToLoginEntity;

    private final LoginEntityToLoginDTOWithoutPfa loginEntityToLoginDTOWithoutPfa;


    public LoginEntity create(LoginEntity loginEntity) {
        loginEntity.setPassword(bCryptPasswordEncoder.encode(loginEntity.getPassword()));

        return loginRepository.save(loginEntity);

    }

    public List<LoginEntity> listAll() {
        return loginRepository.findAll();
    }

    public void save(LoginEntity loginEntity) {
        loginRepository.save(loginEntity);
    }

    public LoginEntity get(Long id) {
        return loginRepository.findById(id).get();
    }

    public void delete(Long id) {
        loginRepository.deleteById(id);
    }



    public List<LoginEntity> getAllUsersWithBusiness() {
        return loginRepository.getAll();}
//
//    public List<LoginEntity> getAllUsersWithBusiness(){
//        return loginRepository.getAll();
//    }
//        public List<LoginEntity> getAllByUserRoles () {
//            return loginRepository.getAllByUserRoles(UserRoles.USER.name());
//        }


        public LoginDTO findById ( long id){

            return loginEntityToLoginDTOWithoutPfa.convert(loginRepository.findById(id));

        }

        @Transactional
        public void update (LoginDTO loginDTO){
            loginDTO.setPassword(bCryptPasswordEncoder.encode(loginDTO.getPassword()));
            LoginEntity convert = loginDTOToLoginEntity.convert(loginDTO);
            loginRepository.save(convert);


        }


}
