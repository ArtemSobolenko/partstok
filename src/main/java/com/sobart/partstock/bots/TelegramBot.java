package com.sobart.partstock.bots;

import com.sobart.partstock.domain.Report;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot {

    private Report report;

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    @Override
    public void onUpdateReceived(Update update) {

        String message = update.getMessage().getText();
        String report = String.format(
                "Hello, %s! \n" +
                        "part: %s in stock now!",
                this.report.getUserReport().getUsername(),
                this.report.getPartReport().getName()
        );
        if (message.equals("/check")) {
            sendMsg(update.getMessage().getChatId().toString(), report);
        }
    }

    public void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return null;
    }

    @Override
    public String getBotToken() {
        return null;
    }
}
