package com.sobart.partstock.controller;

import com.sobart.partstock.domain.Part;
import com.sobart.partstock.domain.Report;
import com.sobart.partstock.domain.User;
import com.sobart.partstock.repository.ReportRepository;
import com.sobart.partstock.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/report")
public class ReportController {

    private ReportRepository reportRepository;

    private ReportService reportService;

    public ReportController(ReportRepository reportRepository, ReportService reportService) {
        this.reportRepository = reportRepository;
        this.reportService = reportService;
    }

    @GetMapping
    public String reportList(Model model) {
        model.addAttribute("reports", reportRepository.findAll());
        return "reportList";
    }

    @GetMapping("/add/{part}/{user}")
    public String add(@PathVariable Part part,
                      @PathVariable User user) {

        if (reportRepository.findByUserReportAndPartReport(user, part) != null) {
            return "redirect:/part";
        }
        Report report = new Report(user, part);
        report.setActive(true);
        reportRepository.save(report);
        return "redirect:/part";
    }

    @GetMapping("/notify/{report}")
    public String notify(
            @PathVariable Report report
    ) {
        report.setActive(false);
        reportRepository.save(report);
        //  reportService.notifyUser(report);  не забыть раскомментировать!!!

        return "redirect:/report";
    }

    @GetMapping("/delete/{report}")
    public String delete(@PathVariable Report report) {
        reportRepository.delete(report);
        return "redirect:/report";
    }
}
