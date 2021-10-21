package com.java.siit.taxcalculator.controller.business;


import com.java.siit.taxcalculator.config.EmailSender;
import com.java.siit.taxcalculator.domain.entity.LoginEntity;
import com.java.siit.taxcalculator.domain.entity.business.SrlEntity;
import com.java.siit.taxcalculator.service.LoginService;
import com.java.siit.taxcalculator.service.business.SrlService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.mail.MessagingException;
import java.util.*;


import java.util.List;


@Controller
@RequestMapping("/user/srl")
@AllArgsConstructor
public class SrlController {

    private final SrlService srlService;
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
            ModelAndView modelAndView = new ModelAndView("srl");
            LoginEntity loginEntity = loginService.get(id);
            System.out.println(loginEntity.getId());

            SrlEntity srlEntity = new SrlEntity();
            saveTotalTaxes(srlEntity);
            srlEntity.setTotalTaxesById(saveTotalTaxes(srlEntity));
            srlEntity.setLoginId(loginEntity.getId());
            modelAndView.addObject("srlEntity", srlEntity);
            modelAndView.addObject("loginEntity", id);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("blank");
            SrlEntity srlEntity = new SrlEntity();
            modelAndView.addObject("srlEntity", srlEntity);
            return modelAndView;
        }


    }


    @PostMapping("/saveAll")
    public RedirectView saveCalcul(SrlEntity srlEntity) {


        srlService.createSrl(srlService.toDto(srlEntity));
        saveTotalTaxes(srlEntity);
        srlEntity.setTotalTaxesById(saveTotalTaxes(srlEntity));
        System.out.println(srlEntity.getTotalTaxesById());

        return new RedirectView("http://localhost:8080/user/srl/taxes/" + Long.toString(srlEntity.getLoginId()));
    }


    @GetMapping("/taxes/{id}")
    public ModelAndView afisareTaxe(@PathVariable("id") Long id, SrlEntity srlEntity) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String emailTemp = loginService.findById(id).getEmail();
        String email;
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();

        }

        if (email.equals(emailTemp)) {
            ModelAndView modelAndView = new ModelAndView("srlTaxes");
            saveTotalTaxes(srlEntity);
            srlEntity.setTotalTaxesById(saveTotalTaxes(srlEntity));

            List<SrlEntity> lista = srlService.findAll(srlEntity);
            List<SrlEntity> list = new ArrayList<SrlEntity>();

            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getLoginId() == id) {
                    list.add(lista.get(i));
                }
            }
            List<SrlEntity> listAni = srlService.findAllByLoginId(id);
            for (int i = 0; i < listAni.size(); i++) {
                for (int j = i + 1; j < listAni.size(); j++) {
                    if (listAni.get(i).getFiscalYear() == listAni.get(j).getFiscalYear()) {
                        listAni.remove(j);
                    }
                }

            }
            modelAndView.addObject("srlLista", list);
            modelAndView.addObject("srlLista2", listAni);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("blank");

            modelAndView.addObject("srlEntity", srlEntity);
            return modelAndView;
        }

    }

    @GetMapping("/taxes/{id}/{fiscalYear}")
    public ModelAndView afisareTaxeDupaAnFiscal(@PathVariable("id") Long id, @PathVariable("fiscalYear") Long fiscalYear, SrlEntity srlEntity) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String emailTemp = loginService.findById(id).getEmail();
        String email;
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();

        }

        if (email.equals(emailTemp)) {

            ModelAndView modelAndView = new ModelAndView("srlTaxes2");
            List<SrlEntity> listAni = srlService.findAllByLoginId(id);
            for (int i = 0; i < listAni.size(); i++) {
                for (int j = i + 1; j < listAni.size(); j++) {
                    if (listAni.get(i).getFiscalYear() == listAni.get(j).getFiscalYear()) {
                        listAni.remove(j);
                    }
                }

            }

            List<SrlEntity> lista = srlService.findAllByFiscalYearAndLoginId(fiscalYear, id);
            Long totalTaxes = lista.stream().mapToLong(x -> x.getTaxesTotal()).sum();

            srlEntity.setTotalTaxesByFiscalYear(totalTaxes);

            modelAndView.addObject("srlLista", lista);
            modelAndView.addObject("srlLista2", listAni);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("blank");
            modelAndView.addObject("srlEntity", srlEntity);
            return modelAndView;
        }
    }

    @PostMapping("/save")
    public RedirectView saveProduct(SrlEntity srlEntity) {

        srlService.updateSrl(srlService.toDto(srlEntity));
        saveTotalTaxes(srlEntity);
        srlEntity.setTotalTaxesById(saveTotalTaxes(srlEntity));
        System.out.println(srlEntity.getTotalTaxesById());

        return new RedirectView("http://localhost:8080/user/srl/taxes/" + Long.toString(srlEntity.getLoginId()));
    }

    @PostMapping("/saveFiscalYear")
    public RedirectView saveFiscalYear(SrlEntity srlEntity) {
        srlService.save(srlEntity);
        saveTotalTaxes(srlEntity);
        srlEntity.setTotalTaxesById(saveTotalTaxes(srlEntity));
        System.out.println(srlEntity.getTotalTaxesById());
        return new RedirectView("http://localhost:8080/user/srl/taxes/fiscalYear/" + Long.toString(srlEntity.getFiscalYear()));
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView loginEdit(@PathVariable(name = "id") Long id) {
        ModelAndView modelAndView = new ModelAndView("editSrlTaxes");

        SrlEntity srlEntity = srlService.get(id);
        modelAndView.addObject("srlEntity", srlEntity);
        return modelAndView;
    }


    @RequestMapping("/delete/{id}")

    public RedirectView deleteTaxes(@PathVariable(name = "id") Long id) {

        SrlEntity srlEntity = srlService.get(id);
        Long nr = srlEntity.getLoginId();
        srlService.delete(id);
        return new RedirectView("http://localhost:8080/user/srl/taxes/" + Long.toString(nr));
    }


    public RedirectView deleteTaxes(@PathVariable(name = "id") Long id, SrlEntity srlEntity) {
        SrlEntity srlEntity1 = srlService.get(id);
        Long nr = srlEntity1.getLoginId();
        srlService.delete(id);

        return new RedirectView("http://localhost:8080/user/srl/taxes/" + Long.toString(nr));
    }


    @GetMapping
    public ModelAndView getPage() {
        ModelAndView modelAndView = new ModelAndView();
        SrlEntity srlEntity = new SrlEntity();
        modelAndView.addObject("srlEntity", srlEntity);
        modelAndView.setViewName("srl");
        return modelAndView;
    }


    @PostMapping()
    public ModelAndView createCalcul(SrlEntity srlEntity) {
        ModelAndView modelAndView = new ModelAndView();
        srlService.createSrl(srlEntity);
        List<SrlEntity> lista = srlService.findAll(srlEntity);
        List<Long> srlTaxesByID = new ArrayList<>();

        methodThatSumAllTaxes(srlEntity, lista, srlTaxesByID);

        modelAndView.addObject("srlEntity", new SrlEntity());
        modelAndView.setViewName("srl");
        return modelAndView;
    }

    private Long methodThatSumAllTaxes(SrlEntity srlEntity, List<SrlEntity> lista, List<Long> srlTaxesByID) {
        long sum = 0;
        for (int i = 0; i < lista.size(); i++) {
            srlTaxesByID.add(lista.get(i).getTaxesTotal());
        }

        for (int i = 0; i < srlTaxesByID.size(); i++)
            sum += srlTaxesByID.get(i);

        srlEntity.setTotalTaxesById(sum);
        return sum;
    }

    @GetMapping("/taxes")
    public ModelAndView afisareTaxe() {
        ModelAndView modelAndView = new ModelAndView("srlTaxes");
        SrlEntity srlEntity = new SrlEntity();
        List<SrlEntity> lista = srlService.findAll(srlEntity);
        System.out.println(srlService.findAll(srlEntity));
        saveTotalTaxes(srlEntity);
        modelAndView.addObject("srlLista", lista);
        return modelAndView;
    }

    private long saveTotalTaxes(SrlEntity srlEntity) {
        List<SrlEntity> lista = srlService.findAll(srlEntity);
        List<Long> srlTaxesByID = new ArrayList<>();

        int sum = 0;
        for (int i = 0; i < lista.size(); i++) {
            srlTaxesByID.add(lista.get(i).getTaxesTotal());
        }

        for (int i = 0; i < srlTaxesByID.size(); i++)
            sum += srlTaxesByID.get(i);

        srlEntity.setTotalTaxesById(sum);
        return sum;
    }

    @PostMapping("/sendEmail")
    public void sendFeedback() throws MessagingException {
        emailSender.sendEmailSrl();
    }

}

