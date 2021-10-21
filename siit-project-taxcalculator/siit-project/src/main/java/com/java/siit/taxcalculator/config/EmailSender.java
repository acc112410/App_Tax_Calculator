package com.java.siit.taxcalculator.config;

import com.java.siit.taxcalculator.domain.entity.business.PfaEntity;
import com.java.siit.taxcalculator.domain.entity.business.SrlEntity;
import com.java.siit.taxcalculator.repository.LoginRepository;
import com.java.siit.taxcalculator.repository.business.PfaRepository;
import com.java.siit.taxcalculator.repository.business.SrlRepository;
import lombok.Data;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;


@Data
@Component
public class EmailSender {
    private final EmailConfiguration emailConfiguration;
    private final PfaRepository pfaRepository;
    private final SrlRepository srlRepository;
    private final LoginRepository loginRepository;

    public void sendEmail() throws MessagingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(this.emailConfiguration.getHost());
        javaMailSender.setPort(this.emailConfiguration.getPort());
        javaMailSender.setUsername(this.emailConfiguration.getUsername());
        javaMailSender.setPassword(this.emailConfiguration.getPassword());

        List<PfaEntity> pfaEntities = pfaRepository.getAllByLoginId(loginRepository.getByEmail(authentication.getName()).getId());

        Long totalTaxes = pfaEntities.stream().mapToLong(x -> x.getTaxesTotal()).sum();

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        String msg1 = "<table class=\"table table-bordered\" align=\"center\">\n" +
                "<thead>\n" +
                "<tr><!--        <th style=\"background-color:#ff0000\" scope=\"row\">An fiscal</th>--></tr>\n" +
                "<tr>\n" +
                "<th style=\"background-color: #00588c; width: 14.575px;\" scope=\"col\">id</th>\n" +
                "<th style=\"background-color: #00588c; width: 77.3125px;\" scope=\"col\">fiscalYear</th>\n" +
                "<th style=\"background-color: #00588c; width: 56.725px;\" scope=\"col\">income</th>\n" +
                "<th style=\"background-color: #00588c; width: 23px;\" scope=\"col\">cui</th>\n" +
                "<th style=\"background-color: #00588c; width: 30.95px;\" scope=\"col\">CAS</th>\n" +
                "<th style=\"background-color: #00588c; width: 40.9px;\" scope=\"col\">CASS</th>\n" +
                "<th style=\"background-color: #00588c; width: 112.537px;\" scope=\"col\">income_Taxes</th>\n" +
                "<th style=\"background-color: #00588c; width: 208.163px;\" scope=\"col\">income_Taxes_Per_Month</th>\n" +
                "<th style=\"background-color: #00588c; width: 215.587px;\" scope=\"col\">dividend_taxes_Per_Month</th>\n" +
                "<th style=\"background-color: #00588c; width: 92.325px;\" scope=\"col\">taxes_Total</th>\n" +
                "<th style=\"background-color: #00588c; width: 0px;\" scope=\"col\">&nbsp;</th>\n" +
                "<!--    <tr>--> <!--        <th type=\"button\" style=\"background-color:#ff0000\" scope=\"row\" class=\"btn btn-primary btn-block\">Taxes total</th>--> <!--    </tr>--></tr>\n" +
                "<!--    <tr>--> <!--        <th type=\"button\" style=\"background-color:#ff0000\" scope=\"row\" class=\"btn btn-primary btn-block\">Taxes total</th>--> <!--    </tr>--></thead>\n" +
                "<tbody>";
        String htmlMsg = msg1;
        for (int i=0; i<pfaEntities.size(); i++){
            htmlMsg = htmlMsg + "<tr>\n" +
                    "<td style=\"background-color: #e75f5f; width: 14.575px;\">" + (pfaEntities.get(i).getId())+ "</td>\n" +
                    "<td style=\"background-color: #e75f5f; width: 77.3125px;\">" + pfaEntities.get(i).getFiscalYear() + "</td>\n" +
                    "<td style=\"background-color: aqua; width: 56.725px;\">" + pfaEntities.get(i).getIncome() +"</td>\n" +
                    "<td style=\"background-color: aqua; width: 23px;\">" + pfaEntities.get(i).getCui() +"</td>\n" +
                    "<td style=\"background-color: aqua; width: 30.95px;\">"+ pfaEntities.get(i).getCAS() +"</td>\n" +
                    "<td style=\"background-color: aqua; width: 40.9px;\">"+ pfaEntities.get(i).getCASS() +"</td>\n" +
                    "<td style=\"background-color: aqua; width: 112.537px;\">"+ pfaEntities.get(i).getIncomeTaxes() +"</td>\n" +
                    "<td style=\"background-color: aqua; width: 208.163px;\">"+ pfaEntities.get(i).getIncomeTaxesPerMonth() +"</td>\n" +
                    "<td style=\"background-color: aqua; width: 215.587px;\">"+ pfaEntities.get(i).getDividendTaxesPerMonth() +"</td>\n" +
                    "<td style=\"background-color: aqua; width: 92.325px;\">"+ pfaEntities.get(i).getTaxesTotal() +"</td>\n" +
                    "</tr>";
        }
        htmlMsg = htmlMsg + "</tbody>\n" +
                "</table>\n" +
                "<table class=\"table table-bordered\" style=\"height: 22px;\" align=\"center\">\n" +
                "<tbody>\n" +
                "<tr style=\"height: 22px;\">\n" +
                "<th style=\"background-color: #00588c; height: 22px; width: 87.1625px;\" scope=\"row\">Total taxes</th>\n" +
                "<td style=\"background-color: #42b9b9; height: 22px; width: 0px;\">" + totalTaxes + "</td>\n" +
                "</tbody>\n" +
                "</table>";
        helper.setText(htmlMsg, true);
        helper.setTo(authentication.getName());
        helper.setSubject("Taxes");
        helper.setFrom("taxcalculator@siit.java");
        javaMailSender.send(mimeMessage);
    }

    public void sendEmailSrl() throws MessagingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(this.emailConfiguration.getHost());
        javaMailSender.setPort(this.emailConfiguration.getPort());
        javaMailSender.setUsername(this.emailConfiguration.getUsername());
        javaMailSender.setPassword(this.emailConfiguration.getPassword());

        List<SrlEntity> srlEntities = srlRepository.getAllByLoginId(loginRepository.getByEmail(authentication.getName()).getId());

        Long totalTaxes = srlEntities.stream().mapToLong(x -> x.getTaxesTotal()).sum();

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        String msg1 = "<table class=\"table table-bordered\" align=\"center\">\n" +
                "<thead>\n" +
                "<tr><!--        <th style=\"background-color:#ff0000\" scope=\"row\">An fiscal</th>--></tr>\n" +
                "<tr>\n" +
                "<th style=\"background-color: #00588c; width: 14.575px;\" scope=\"col\">id</th>\n" +
                "<th style=\"background-color: #00588c; width: 77.3125px;\" scope=\"col\">fiscalYear</th>\n" +
                "<th style=\"background-color: #00588c; width: 56.725px;\" scope=\"col\">income</th>\n" +
                "<th style=\"background-color: #00588c; width: 23px;\" scope=\"col\">cui</th>\n" +
                "<th style=\"background-color: #00588c; width: 30.95px;\" scope=\"col\">CAS</th>\n" +
                "<th style=\"background-color: #00588c; width: 40.9px;\" scope=\"col\">CASS</th>\n" +
                "<th style=\"background-color: #00588c; width: 112.537px;\" scope=\"col\">income_Taxes</th>\n" +
                "<th style=\"background-color: #00588c; width: 208.163px;\" scope=\"col\">income_Taxes_Per_Month</th>\n" +
                "<th style=\"background-color: #00588c; width: 215.587px;\" scope=\"col\">dividend_taxes_Per_Month</th>\n" +
                "<th style=\"background-color: #00588c; width: 92.325px;\" scope=\"col\">taxes_Total</th>\n" +
                "<th style=\"background-color: #00588c; width: 0px;\" scope=\"col\">&nbsp;</th>\n" +
                "<!--    <tr>--> <!--        <th type=\"button\" style=\"background-color:#ff0000\" scope=\"row\" class=\"btn btn-primary btn-block\">Taxes total</th>--> <!--    </tr>--></tr>\n" +
                "<!--    <tr>--> <!--        <th type=\"button\" style=\"background-color:#ff0000\" scope=\"row\" class=\"btn btn-primary btn-block\">Taxes total</th>--> <!--    </tr>--></thead>\n" +
                "<tbody>";
        String htmlMsg = msg1;
        for (int i=0; i<srlEntities.size(); i++){
            htmlMsg = htmlMsg + "<tr>\n" +
                    "<td style=\"background-color: #e75f5f; width: 14.575px;\">" + (srlEntities.get(i).getId())+ "</td>\n" +
                    "<td style=\"background-color: #e75f5f; width: 77.3125px;\">" + srlEntities.get(i).getFiscalYear() + "</td>\n" +
                    "<td style=\"background-color: aqua; width: 56.725px;\">" + srlEntities.get(i).getIncome() +"</td>\n" +
                    "<td style=\"background-color: aqua; width: 23px;\">" + srlEntities.get(i).getCui() +"</td>\n" +
                    "<td style=\"background-color: aqua; width: 30.95px;\">"+ srlEntities.get(i).getCAS() +"</td>\n" +
                    "<td style=\"background-color: aqua; width: 40.9px;\">"+ srlEntities.get(i).getCASS() +"</td>\n" +
                    "<td style=\"background-color: aqua; width: 112.537px;\">"+ srlEntities.get(i).getIncomeTaxes() +"</td>\n" +
                    "<td style=\"background-color: aqua; width: 208.163px;\">"+ srlEntities.get(i).getIncomeTaxesPerMonth() +"</td>\n" +
                    "<td style=\"background-color: aqua; width: 215.587px;\">"+ srlEntities.get(i).getDividendTaxesPerMonth() +"</td>\n" +
                    "<td style=\"background-color: aqua; width: 92.325px;\">"+ srlEntities.get(i).getTaxesTotal() +"</td>\n" +
                    "</tr>";
        }
        htmlMsg = htmlMsg + "</tbody>\n" +
                "</table>\n" +
                "<table class=\"table table-bordered\" style=\"height: 22px;\" align=\"center\">\n" +
                "<tbody>\n" +
                "<tr style=\"height: 22px;\">\n" +
                "<th style=\"background-color: #00588c; height: 22px; width: 87.1625px;\" scope=\"row\">Total taxes</th>\n" +
                "<td style=\"background-color: #42b9b9; height: 22px; width: 0px;\">" + totalTaxes + "</td>\n" +
                "</tbody>\n" +
                "</table>";
        helper.setText(htmlMsg, true);
        helper.setTo(authentication.getName());
        helper.setSubject("Taxes");
        helper.setFrom("taxcalculator@siit.java");
        javaMailSender.send(mimeMessage);
    }
   public void sendEmailFiscalYear(Long fiscalYear) throws MessagingException {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
            javaMailSender.setHost(this.emailConfiguration.getHost());
            javaMailSender.setPort(this.emailConfiguration.getPort());
            javaMailSender.setUsername(this.emailConfiguration.getUsername());
            javaMailSender.setPassword(this.emailConfiguration.getPassword());

        List<PfaEntity> pfaEntities = pfaRepository.findAllByFiscalYearAndLoginId(fiscalYear, loginRepository.getByEmail(authentication.getName()).getId());

        Long totalTaxes = pfaEntities.stream().mapToLong(x -> x.getTaxesTotal()).sum();

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            String msg1 = "<table class=\"table table-bordered\" align=\"center\">\n" +
                    "<thead>\n" +
                    "<tr><!--        <th style=\"background-color:#ff0000\" scope=\"row\">An fiscal</th>--></tr>\n" +
                    "<tr>\n" +
                    "<th style=\"background-color: #00588c; width: 14.575px;\" scope=\"col\">id</th>\n" +
                    "<th style=\"background-color: #00588c; width: 77.3125px;\" scope=\"col\">fiscalYear</th>\n" +
                    "<th style=\"background-color: #00588c; width: 56.725px;\" scope=\"col\">income</th>\n" +
                    "<th style=\"background-color: #00588c; width: 23px;\" scope=\"col\">cui</th>\n" +
                    "<th style=\"background-color: #00588c; width: 30.95px;\" scope=\"col\">CAS</th>\n" +
                    "<th style=\"background-color: #00588c; width: 40.9px;\" scope=\"col\">CASS</th>\n" +
                    "<th style=\"background-color: #00588c; width: 112.537px;\" scope=\"col\">income_Taxes</th>\n" +
                    "<th style=\"background-color: #00588c; width: 208.163px;\" scope=\"col\">income_Taxes_Per_Month</th>\n" +
                    "<th style=\"background-color: #00588c; width: 215.587px;\" scope=\"col\">dividend_taxes_Per_Month</th>\n" +
                    "<th style=\"background-color: #00588c; width: 92.325px;\" scope=\"col\">taxes_Total</th>\n" +
                    "<th style=\"background-color: #00588c; width: 0px;\" scope=\"col\">&nbsp;</th>\n" +
                    "<!--    <tr>--> <!--        <th type=\"button\" style=\"background-color:#ff0000\" scope=\"row\" class=\"btn btn-primary btn-block\">Taxes total</th>--> <!--    </tr>--></tr>\n" +
                    "<!--    <tr>--> <!--        <th type=\"button\" style=\"background-color:#ff0000\" scope=\"row\" class=\"btn btn-primary btn-block\">Taxes total</th>--> <!--    </tr>--></thead>\n" +
                    "<tbody>";
            String htmlMsg = msg1;
            for (int i=0; i<pfaEntities.size(); i++){
                htmlMsg = htmlMsg + "<tr>\n" +
                        "<td style=\"background-color: #e75f5f; width: 14.575px;\">" + (pfaEntities.get(i).getId())+ "</td>\n" +
                        "<td style=\"background-color: #e75f5f; width: 77.3125px;\">" + pfaEntities.get(i).getFiscalYear() + "</td>\n" +
                        "<td style=\"background-color: aqua; width: 56.725px;\">" + pfaEntities.get(i).getIncome() +"</td>\n" +
                        "<td style=\"background-color: aqua; width: 23px;\">" + pfaEntities.get(i).getCui() +"</td>\n" +
                        "<td style=\"background-color: aqua; width: 30.95px;\">"+ pfaEntities.get(i).getCAS() +"</td>\n" +
                        "<td style=\"background-color: aqua; width: 40.9px;\">"+ pfaEntities.get(i).getCASS() +"</td>\n" +
                        "<td style=\"background-color: aqua; width: 112.537px;\">"+ pfaEntities.get(i).getIncomeTaxes() +"</td>\n" +
                        "<td style=\"background-color: aqua; width: 208.163px;\">"+ pfaEntities.get(i).getIncomeTaxesPerMonth() +"</td>\n" +
                        "<td style=\"background-color: aqua; width: 215.587px;\">"+ pfaEntities.get(i).getDividendTaxesPerMonth() +"</td>\n" +
                        "<td style=\"background-color: aqua; width: 92.325px;\">"+ pfaEntities.get(i).getTaxesTotal() +"</td>\n" +
                        "</tr>";
            }
            htmlMsg = htmlMsg + "</tbody>\n" +
                    "</table>\n" +
                    "<table class=\"table table-bordered\" style=\"height: 22px;\" align=\"center\">\n" +
                    "<tbody>\n" +
                    "<tr style=\"height: 22px;\">\n" +
                    "<th style=\"background-color: #00588c; height: 22px; width: 87.1625px;\" scope=\"row\">Total taxes</th>\n" +
                    "<td style=\"background-color: #42b9b9; height: 22px; width: 0px;\">" + totalTaxes + "</td>\n" +
                    "</tbody>\n" +
                    "</table>";
            helper.setText(htmlMsg, true);
            helper.setTo(authentication.getName());
            helper.setSubject("Taxes");
            helper.setFrom("taxcalculator@siit.java");
            javaMailSender.send(mimeMessage);
        }
    }

