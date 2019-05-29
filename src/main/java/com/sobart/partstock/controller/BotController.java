package com.sobart.partstock.controller;

import com.sobart.partstock.domain.Report;
import com.sobart.partstock.service.TelegramBotService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BotController {

    private TelegramBotService telegramBotService;

    public BotController(TelegramBotService telegramBotService) {
        this.telegramBotService = telegramBotService;
        telegramBotService.botInit();
    }

    @GetMapping("/report/bot/{report}")
    public String notify(@PathVariable Report report) {

        telegramBotService.getTelegramBot().setReport(report);

        return "redirect:/part";
    }

}
