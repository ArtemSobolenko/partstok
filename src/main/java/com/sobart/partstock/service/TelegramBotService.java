package com.sobart.partstock.service;

import com.sobart.partstock.bots.TelegramBot;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class TelegramBotService {

    private TelegramBot telegramBot;

    public TelegramBot getTelegramBot() {
        return telegramBot;
    }

    public void botInit() {
        // Initialize Api Context
        ApiContextInitializer.init();
        // Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();
        //Registration bot
        try {

            this.telegramBot = new TelegramBot();

            botsApi.registerBot(this.telegramBot);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}
