package com.java.siit.taxcalculator.controller;


import com.java.siit.taxcalculator.config.UserRoles;
import com.java.siit.taxcalculator.config.WebMvcConfig;
import com.java.siit.taxcalculator.domain.entity.LoginEntity;
import com.java.siit.taxcalculator.domain.entity.business.PfaEntity;
import com.java.siit.taxcalculator.domain.model.business.PfaDTO;
import com.java.siit.taxcalculator.service.LoginService;
import com.java.siit.taxcalculator.service.UserRolesService;
import com.java.siit.taxcalculator.service.business.PfaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import com.java.siit.taxcalculator.domain.model.LoginDTO;
import com.java.siit.taxcalculator.mapper.LoginEntityToLoginDTOMapper;
import javassist.NotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequestMapping
@AllArgsConstructor
public class LoginController {

    @Autowired
    private final LoginService service;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private final PfaService pfaService;

    @Autowired
    private final UserRolesService userRolesService;




    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/accessDenied")
    public ModelAndView accessDenied() {
        ModelAndView modelAndView = new ModelAndView("blank");
        PfaEntity pfaEntity = new PfaEntity();
        modelAndView.addObject("pfaEntity", pfaEntity);
        return modelAndView;
    }


    @RequestMapping("/user/edit/{id}")
    public ModelAndView editUser(@PathVariable(name = "id") int id) throws NotFoundException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String emailTemp = service.findById(id).getEmail();
        String email;
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();

        }

        if (email.equals(emailTemp)) {

            ModelAndView mav = new ModelAndView("edit_user");
            LoginDTO login = service.findById(id);
            String a = login.getPassword();
            login.setPassword(bCryptPasswordEncoder.encode(a));
            mav.addObject("login", login);

            return mav;
        } else {
            ModelAndView modelAndView = new ModelAndView("blank");
            PfaEntity pfaEntity = new PfaEntity();
            modelAndView.addObject("pfaEntity", pfaEntity);
            return modelAndView;
        }
    }

    @GetMapping("/register")
    public ModelAndView register() {

        ModelAndView modelAndView = new ModelAndView();
        LoginEntity loginEntity = new LoginEntity();
        modelAndView.addObject("loginEntity", loginEntity);
        modelAndView.setViewName("register");
        return modelAndView;
    }


    @PutMapping(value = "/user/update")
    public RedirectView editUser(@ModelAttribute("login") LoginDTO loginDTO) throws NotFoundException {

        service.update(loginDTO);
        return new RedirectView("http://localhost:8080/user/edit/" + Long.toString(loginDTO.getId()));

    }

    @PostMapping("/register")
    public ModelAndView create(LoginEntity loginEntity, BindingResult bindingResult, ModelMap modelMap) {

        System.out.println("login " + loginEntity);
        loginEntity.setEnabled(true);

        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Please correct the errors in form!");
            modelMap.addAttribute("bindingResult", bindingResult);
        } else {

            LoginEntity loginEntity1 = service.create(loginEntity);


            modelAndView.addObject("successMessage", "User is registered successfully!");
        }
        modelAndView.addObject("loginEntity", new LoginEntity());
        userRolesService.create(loginEntity);
        modelAndView.setViewName("register");
        return modelAndView;
    }
}
