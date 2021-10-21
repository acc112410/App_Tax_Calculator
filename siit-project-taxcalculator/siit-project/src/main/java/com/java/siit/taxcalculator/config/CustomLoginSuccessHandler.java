package com.java.siit.taxcalculator.config;

import com.java.siit.taxcalculator.domain.entity.LoginEntity;
import com.java.siit.taxcalculator.repository.LoginRepository;

import com.java.siit.taxcalculator.repository.UserRolesRepository;
import com.java.siit.taxcalculator.service.LoginService;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@AllArgsConstructor
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {


    private final LoginRepository loginRepository;
    private final LoginService loginService;
    private final UserRolesRepository userRolesRepository;

    


    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {

        String targetUrl = determineTargetUrl(authentication);
        if (response.isCommitted()) {
            return;
        }
        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected String determineTargetUrl(Authentication authentication) {




        LoginEntity login = loginRepository.getByEmail(authentication.getName());
        Long id = login.getId();
        String stringId= id.toString();
        UserRoles userRoles = userRolesRepository.getByEmail(login.getEmail());
        String role=userRoles.getRoles();

        if(role.equals("ROLE_ADMIN")){
            System.out.println("works");
            return "/admin/";
        } else switch (login.getTypeOfBusiness()) {
            case "SRL":
                return "user/srl/"+stringId;
            case "PFA":
                return "user/pfa/"+stringId;
            default:
                return "failed";
        }




    }



}
