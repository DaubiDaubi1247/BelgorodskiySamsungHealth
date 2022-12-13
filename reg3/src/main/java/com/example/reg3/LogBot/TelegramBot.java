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
    private final ChatIdInfoRepository chatIdInfoRepository;

    private StringBuilder accumulateInfo = new StringBuilder();

    public TelegramBot(BotConfig config, List<Long> idOfChats, ChatIdInfoRepository chatIdInfoRepository) {
        this.config = config;
        this.chatIdInfoRepository = chatIdInfoRepository;
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
            String userName = update.getMessage().getChat().getFirstName();
            if ("/start".equals(messageText)) {
                startCommandReceived(chatId, userName);
            } else if ("/stopLogs".equals(messageText)) {
                unsubUserFromLogList(chatId, userName);

            } else {
                if (messageText.equals(config.getPassword())) {
                    subUserToLogList(chatId, userName);
                } else {
                    sendMessge(chatId, "команда не опознана");
                }
            }
        }
    }

    private void unsubUserFromLogList(long chatId, String firstName) {
        var answer = chatIdInfoRepository.findChatIdInfoByChatId(chatId);

        if (answer.isPresent()) {
            chatIdInfoRepository.deleteById(answer.get().getId());
            String msg = firstName + ", теперь вы отписаны от рассылки логов";
            sendMessge(chatId, msg);
        } else {
            String msg = firstName + ", вы не были подписнаы на рассылку логов";
            sendMessge(chatId, msg);
        }
    }

    public void sendInfo(String msg) {
        accumulateInfo.append("(INFO): ").append(msg).append("\n\n");
    }

    public void sendError(String msg) {
        accumulateInfo.append("(ERROR): ").append(msg).append("\n\n");
    }

    public void sendWarning(String msg)
    {
        accumulateInfo.append("(WARNING): ").append(msg).append("\n\n");
    }

    public void executeSendLog() {
        if (accumulateInfo.isEmpty()) {
            sendLog("Пустой лог");
        }else {
            sendLog(accumulateInfo.toString());
            accumulateInfo = new StringBuilder();
        }
    }
    private void sendLog(String msg) {
        var listIdOfChats = chatIdInfoRepository.findAll();
        for (var user : listIdOfChats) {
            sendMessge(user.getChatId(), msg);
        }
    }

    private void subUserToLogList(long chatId, String firstName) {
        var answer = chatIdInfoRepository.findChatIdInfoByChatId(chatId);

        if (answer.isEmpty()) {
            chatIdInfoRepository.save(new ChatIdInfo(null, chatId, firstName));
            String msg = firstName + ", теперь вы подписаны на рассылку логов";
            sendMessge(chatId, msg);
        } else {
            String msg = firstName + ", выуже подписаны на рассылку";
            sendMessge(chatId, msg);
        }
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
            //throw new RuntimeException("Ошибка в телеграм боте при отправке сообщения");
        }
    }
}