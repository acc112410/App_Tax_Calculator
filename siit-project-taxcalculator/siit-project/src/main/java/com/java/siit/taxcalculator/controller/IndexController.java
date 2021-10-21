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
import org.springframework.security.core.parameters.P;
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
public class IndexController {


    @Autowired
    private final PfaService pfaService;


    @GetMapping("/index")
    public ModelAndView homepagePFA() {

        ModelAndView modelAndView = new ModelAndView("homepage2");
        PfaEntity pfaEntity = new PfaEntity();
        modelAndView.addObject("pfaEntity", pfaEntity);

        return modelAndView;
    }


    @GetMapping("/index/srl")
    public ModelAndView homepageSRL() {

        ModelAndView modelAndView = new ModelAndView("homepage3");
        PfaEntity pfaEntity = new PfaEntity();
        modelAndView.addObject("pfaEntity", pfaEntity);

        return modelAndView;
    }

    @GetMapping("/index/aboutUs")
    public ModelAndView aboutUs() {
        ModelAndView modelAndView = new ModelAndView("aboutUs");
        return modelAndView;
    }
    @GetMapping("/index/contact")
    public ModelAndView contact() {
        ModelAndView modelAndView = new ModelAndView("contact");
        return modelAndView;
    }
    @GetMapping("/index/help")
    public ModelAndView help() {
        ModelAndView modelAndView = new ModelAndView("help");
        return modelAndView;
    }

}
