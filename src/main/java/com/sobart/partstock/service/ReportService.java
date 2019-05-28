package com.sobart.partstock.service;

import com.sobart.partstock.domain.Report;
import com.sobart.partstock.repository.ReportRepository;
import org.springframework.stereotype.Service;

@Service
public class ReportService {


    private MailSender mailSender;

    public ReportService(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void notifyUser(Report report){

        String message = String.format(
                "Hello, %s! \n" +
                        "part: %s in stock now!",
                report.getUserReport().getUsername(),
                report.getPartReport().getName()
        );

        mailSender.send(report.getUserReport().getEmail(), "Your report from part stock", message);

    }

}
