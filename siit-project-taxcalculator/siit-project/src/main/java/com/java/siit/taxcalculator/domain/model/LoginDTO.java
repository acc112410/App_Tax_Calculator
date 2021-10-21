package com.java.siit.taxcalculator.domain.model;


import com.java.siit.taxcalculator.domain.model.business.PfaDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {


    private long id;


    private String firstName;

    private String lastName;


    private String email;

    private String password;


    private boolean enabled;

    private String typeOfBusiness;





    public boolean getEnabled() {
        return enabled;
    }
}
