package ru.study.codeinterviewbot.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.study.codeinterviewbot.bot.CodeInterviewBot;

@Component
public class MessageSender {
    @Autowired
    private final ApplicationContext applicationContext;

    public MessageSender(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void send(SendMessage sendMessage) throws TelegramApiException {

        CodeInterviewBot bot = applicationContext.getBean(CodeInterviewBot.class);
        bot.execute(sendMessage);
    }
}