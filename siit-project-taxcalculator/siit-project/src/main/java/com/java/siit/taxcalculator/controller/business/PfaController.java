package com.java.siit.taxcalculator.controller.business;


import com.java.siit.taxcalculator.config.EmailSender;
import com.java.siit.taxcalculator.domain.entity.LoginEntity;
import com.java.siit.taxcalculator.domain.entity.business.PfaEntity;
import com.java.siit.taxcalculator.service.LoginService;
import com.java.siit.taxcalculator.service.business.PfaService;
import lombok.AllArgsConstructor;
//import org.jetbrains.annotations.NotNull;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;

import com.java.siit.taxcalculator.domain.entity.business.PfaEntity;
import com.java.siit.taxcalculator.service.business.PfaService;
import lombok.AllArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;


//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSenderImpl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.servlet.view.RedirectView;

import javax.mail.MessagingException;
import java.util.*;


import java.util.List;


@Controller
@RequestMapping("/user/pfa")
@AllArgsConstructor
public class PfaController {

    private final PfaService pfaService;
    private final LoginService loginService;
    private final EmailSender emailSender;

    @RequestMapping("/{id}")

    public ModelAndView createCalcul(@PathVariable(name = "id") Long id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String emailTemp = loginService.findById(id).getEmail();
        String email;
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();

        }

        if (email.equals(emailTemp)) {
            ModelAndView modelAndView = new ModelAndView("pfa");
            LoginEntity loginEntity = loginService.get(id);
            System.out.println(loginEntity.getId());

            PfaEntity pfaEntity = new PfaEntity();
            saveTotalTaxes(pfaEntity);
            pfaEntity.setTotalTaxesById(saveTotalTaxes(pfaEntity));
            pfaEntity.setLoginId(loginEntity.getId());
            modelAndView.addObject("pfaEntity", pfaEntity);
            modelAndView.addObject("loginEntity", id);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("blank");
            PfaEntity pfaEntity = new PfaEntity();
            modelAndView.addObject("pfaEntity", pfaEntity);
            return modelAndView;
        }


    }


    @PostMapping("/saveAll")
    public RedirectView saveCalcul(PfaEntity pfaEntity) {


        pfaService.createPfa(pfaService.toDto(pfaEntity));
        saveTotalTaxes(pfaEntity);
        pfaEntity.setTotalTaxesById(saveTotalTaxes(pfaEntity));
        System.out.println(pfaEntity.getTotalTaxesById());

        return new RedirectView("http://localhost:8080/user/pfa/taxes/" + Long.toString(pfaEntity.getLoginId()));
    }


    @GetMapping("/taxes/{id}")
    public ModelAndView afisareTaxe(@PathVariable("id") Long id, PfaEntity pfaEntity) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String emailTemp = loginService.findById(id).getEmail();
        String email;
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();

        }

        if (email.equals(emailTemp)) {
            ModelAndView modelAndView = new ModelAndView("pfaTaxes");
            saveTotalTaxes(pfaEntity);
            pfaEntity.setTotalTaxesById(saveTotalTaxes(pfaEntity));

            List<PfaEntity> lista = pfaService.findAll(pfaEntity);
            List<PfaEntity> list = new ArrayList<PfaEntity>();

            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getLoginId() == id) {
                    list.add(lista.get(i));
                }
            }
            List<PfaEntity> listAni = pfaService.findAllByLoginId(id);
            for (int i = 0; i < listAni.size(); i++) {
                for (int j = i + 1; j < listAni.size(); j++) {
                    if (listAni.get(i).getFiscalYear() == listAni.get(j).getFiscalYear()) {
                        listAni.remove(j);
                    }
                }

            }
            modelAndView.addObject("pfaLista", list);
            modelAndView.addObject("pfaLista2", listAni);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("blank");

            modelAndView.addObject("pfaEntity", pfaEntity);
            return modelAndView;
        }

    }

    @GetMapping("/taxes/{id}/{fiscalYear}")
    public ModelAndView afisareTaxeDupaAnFiscal(@PathVariable("id") Long id, @PathVariable("fiscalYear") Long fiscalYear, PfaEntity pfaEntity) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String emailTemp = loginService.findById(id).getEmail();
        String email;
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();

        }

        if (email.equals(emailTemp)) {

            ModelAndView modelAndView = new ModelAndView("pfaTaxes2");
            List<PfaEntity> listAni = pfaService.findAllByLoginId(id);
            for (int i = 0; i < listAni.size(); i++) {
                for (int j = i + 1; j < listAni.size(); j++) {
                    if (listAni.get(i).getFiscalYear() == listAni.get(j).getFiscalYear()) {
                        listAni.remove(j);
                    }
                }

            }

            List<PfaEntity> lista = pfaService.findAllByFiscalYearAndLoginId(fiscalYear, id);
            Long totalTaxes = lista.stream().mapToLong(x -> x.getTaxesTotal()).sum();

            pfaEntity.setTotalTaxesByFiscalYear(totalTaxes);

            modelAndView.addObject("pfaLista", lista);
            modelAndView.addObject("pfaLista2", listAni);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("blank");
            modelAndView.addObject("pfaEntity", pfaEntity);
            return modelAndView;
        }
    }

    @PostMapping("/save")
    public RedirectView saveProduct(PfaEntity pfaEntity) {

        pfaService.updatePfa(pfaService.toDto(pfaEntity));
        saveTotalTaxes(pfaEntity);
        pfaEntity.setTotalTaxesById(saveTotalTaxes(pfaEntity));
        System.out.println(pfaEntity.getTotalTaxesById());

        return new RedirectView("http://localhost:8080/user/pfa/taxes/" + Long.toString(pfaEntity.getLoginId()));
    }

    @PostMapping("/saveFiscalYear")
    public RedirectView saveFiscalYear(PfaEntity pfaEntity) {
        pfaService.save(pfaEntity);
        saveTotalTaxes(pfaEntity);
        pfaEntity.setTotalTaxesById(saveTotalTaxes(pfaEntity));
        System.out.println(pfaEntity.getTotalTaxesById());
        return new RedirectView("http://localhost:8080/user/pfa/taxes/fiscalYear/" + Long.toString(pfaEntity.getFiscalYear()));
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView loginEdit(@PathVariable(name = "id") Long id) {
        ModelAndView modelAndView = new ModelAndView("editPfaTaxes");

        PfaEntity pfaEntity = pfaService.get(id);
        modelAndView.addObject("pfaEntity", pfaEntity);
        return modelAndView;
    }


    @RequestMapping("/delete/{id}")

    public RedirectView deleteTaxes(@PathVariable(name = "id") Long id) {

        PfaEntity pfaEntity1 = pfaService.get(id);
        Long nr = pfaEntity1.getLoginId();
        pfaService.delete(id);
        return new RedirectView("http://localhost:8080/user/pfa/taxes/" + Long.toString(nr));
    }


    public RedirectView deleteTaxes(@PathVariable(name = "id") Long id, PfaEntity pfaEntity) {
        PfaEntity pfaEntity1 = pfaService.get(id);
        Long nr = pfaEntity1.getLoginId();
        pfaService.delete(id);
//        saveTotalTaxes(pfaEntity);
//        System.out.println(pfaEntity.getTotalTaxesById());
        return new RedirectView("http://localhost:8080/user/pfa/taxes/" + Long.toString(nr));
    }


    @GetMapping
    public ModelAndView getPage() {
        ModelAndView modelAndView = new ModelAndView();
        PfaEntity pfaEntity = new PfaEntity();
        modelAndView.addObject("pfaEntity", pfaEntity);
        modelAndView.setViewName("pfa");
        return modelAndView;
    }


    @PostMapping()
    public ModelAndView createCalcul(PfaEntity pfaEntity) {
        ModelAndView modelAndView = new ModelAndView();
        pfaService.createPfa(pfaEntity);
        List<PfaEntity> lista = pfaService.findAll(pfaEntity);
        List<Long> pfaTaxesByID = new ArrayList<>();

        methodThatSumAllTaxes(pfaEntity, lista, pfaTaxesByID);

        modelAndView.addObject("pfaEntity", new PfaEntity());
        modelAndView.setViewName("pfa");
        return modelAndView;
    }

    private Long methodThatSumAllTaxes(PfaEntity pfaEntity, List<PfaEntity> lista, List<Long> pfaTaxesByID) {
        long sum = 0;
        for (int i = 0; i < lista.size(); i++) {
            pfaTaxesByID.add(lista.get(i).getTaxesTotal());
        }

        for (int i = 0; i < pfaTaxesByID.size(); i++)
            sum += pfaTaxesByID.get(i);

        pfaEntity.setTotalTaxesById(sum);
        return sum;
    }

    @GetMapping("/taxes")
    public ModelAndView afisareTaxe() {
        ModelAndView modelAndView = new ModelAndView("pfaTaxes");
        PfaEntity pfaEntity = new PfaEntity();
        List<PfaEntity> lista = pfaService.findAll(pfaEntity);
        System.out.println(pfaService.findAll(pfaEntity));
        saveTotalTaxes(pfaEntity);
        modelAndView.addObject("pfaLista", lista);
        return modelAndView;
    }

    private long saveTotalTaxes(PfaEntity pfaEntity) {
        List<PfaEntity> lista = pfaService.findAll(pfaEntity);
        List<Long> pfaTaxesByID = new ArrayList<>();

        int sum = 0;
        for (int i = 0; i < lista.size(); i++) {
            pfaTaxesByID.add(lista.get(i).getTaxesTotal());
        }

        for (int i = 0; i < pfaTaxesByID.size(); i++)
            sum += pfaTaxesByID.get(i);

        pfaEntity.setTotalTaxesById(sum);
        return sum;
    }

    @PostMapping("/sendEmail")
    public void sendFeedback() throws MessagingException {
        emailSender.sendEmail();
    }

    @RequestMapping("/sendEmailFiscalYear/{id}/{fiscalYear}")
    public RedirectView sendFeedback(@PathVariable (name ="fiscalYear") Long fiscalYear, @PathVariable(name="id") Long id) throws MessagingException {
        emailSender.sendEmailFiscalYear(fiscalYear);
        return new RedirectView("http://localhost:8080/user/pfa/taxes/" + Long.toString(id) +"/" + Long.toString(fiscalYear));
    }

}

