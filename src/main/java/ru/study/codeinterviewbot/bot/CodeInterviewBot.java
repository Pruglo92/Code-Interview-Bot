package ru.study.codeinterviewbot.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class CodeInterviewBot extends TelegramLongPollingBot {

    @Value("${telegram.bot.username}")
    private String botUsername;

    public CodeInterviewBot(@Value("${telegram.bot.token}") String botToken) {
        super(botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();

            if (messageText.equals("/start")) {
                SendMessage messageToSend = new SendMessage();
                messageToSend.setChatId(chatId);
                messageToSend.setText("Привет " + update.getMessage().getChat().getFirstName() + "! Я помогу тебе подготовиться к собеседованию.");

                try {
                    execute(messageToSend);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }
}