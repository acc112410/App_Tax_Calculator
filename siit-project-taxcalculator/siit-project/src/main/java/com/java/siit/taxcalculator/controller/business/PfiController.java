package com.java.siit.taxcalculator.controller.business;



import com.java.siit.taxcalculator.domain.entity.business.PfaEntity;

import com.java.siit.taxcalculator.domain.entity.business.PfiEntity;
import com.java.siit.taxcalculator.service.business.PfiService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/user/pfi")
@AllArgsConstructor

public class PfiController {

    private final PfiService pfiService;

    @GetMapping
    public ModelAndView getPage() {
        ModelAndView modelAndView = new ModelAndView();
        PfiEntity pfiEntity = new PfiEntity();
        modelAndView.addObject("pfiEntity", pfiEntity);
        modelAndView.setViewName("pfi");
        return modelAndView;
    }

//    @PostMapping()
//    public ModelAndView createCalcul(PfiEntity pfiEntity) {
//
//        ModelAndView modelAndView = new ModelAndView();
//        pfiService.createPfi(pfiEntity);
//        modelAndView.addObject("pfiEntity", new PfiEntity());
//        modelAndView.setViewName("pfi");
//        return modelAndView;
//    }
//
//    @GetMapping("/taxes")
//    public void afisareTaxe(PfiEntity pfiEntity) {
//        ModelAndView modelAndView = new ModelAndView();
//        pfiService.findAll(pfiEntity);
//        modelAndView.addObject("pfiTaxes", new PfiEntity());
//        modelAndView.setViewName("pfiTaxes");
//        System.out.println(pfiService.findAll(pfiEntity));
//    }
//
//
//    @GetMapping("/taxes/{user_id}")
//    String afisareTaxe(@PathVariable("user_id") Long userId, Principal principal, Model model) {
//        PfiEntity pfiEntity = new PfiEntity();
//        List<PfiEntity> lista = pfiService.findAll(pfiEntity);
//        System.out.println(pfiService.findAll(pfiEntity));
//        model.addAttribute("pfiLista", lista);
//        return "pfiTaxes";
//
//    }
//    @PostMapping("/save")
//    public RedirectView saveProduct(@ModelAttribute("pfiEntity") PfiEntity pfiEntity) {
//        pfiService.createPfi(pfiEntity);
//        return new RedirectView("http://localhost:8080/pfi/taxes/1");
//    }
//
//    @RequestMapping("/edit/{id}")
//    public ModelAndView loginEdit(@PathVariable(name = "id") Long id) {
//        ModelAndView modelAndView = new ModelAndView("editPfiTaxes");
//        PfiEntity pfiEntity = pfiService.get(id);
//        pfiService.delete(id);
//        modelAndView.addObject("pfiEntity", pfiEntity);
//        return modelAndView;
//    }
//
//    @RequestMapping("/delete/{id}")
//    public RedirectView deleteTaxes(@PathVariable(name = "id") Long id) {
//        pfiService.delete(id);
//        return new RedirectView("http://localhost:8080/pfi/taxes/1");
//    }

}
