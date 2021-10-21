package com.java.siit.taxcalculator.config;


import com.java.siit.taxcalculator.domain.entity.LoginEntity;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component("userSecurity")
public class UserSecurity {
        public boolean hasUserId(Authentication authentication, Long id) {
            Object principal = authentication.getPrincipal();
            Long currentID;
            if (principal instanceof LoginEntity){
              currentID = ((LoginEntity)principal).getId();
            } else { String temp = principal.toString();
                 currentID = Long.parseLong(temp);
            }
         boolean answer;
            if (currentID == id) {
                answer = true;
            } else {
                answer = false;
            }
            return  answer;
        }
    }

