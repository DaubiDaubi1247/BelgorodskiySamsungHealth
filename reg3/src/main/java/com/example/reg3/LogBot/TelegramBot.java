package com.example.reg3.LogBot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
public class TelegramBot
        extends TelegramLongPollingBot {

    final BotConfig config;
    private final List<Long> listIdOfChats;

    public TelegramBot(BotConfig config, List<Long> idOfChats) {
        this.config = config;
        this.listIdOfChats = idOfChats;
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {

        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            if ("/start".equals(messageText)) {
                startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
            } else {
                if (messageText.equals(config.getPassword())) {
                    subUserTologList(chatId, update.getMessage().getChat().getFirstName());
                } else {
                    sendMessge(chatId, "команда не опознана");
                }
            }
        }

    }

    public void sendLog(String msg) {
        for (var id: listIdOfChats) {
            sendMessge(id, msg);
        }
    }

    private void subUserTologList(long chatId, String firstName) {
        listIdOfChats.add(chatId);
        String msg = firstName + ", теперь вы подписаны на рассылку логов";
        sendMessge(chatId, msg);
    }


    private void startCommandReceived(long chatId, String userName) {
        String anser = "welcome to the club " + userName + "!\n" +
                "Введи пароль для получения логов с сервера";
        sendMessge(chatId, anser);
    }

    private void sendMessge(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException("Ошибка в телеграм боте при отправке сообщения");
        }
    }
}