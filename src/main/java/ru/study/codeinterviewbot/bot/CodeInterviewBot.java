package ru.study.codeinterviewbot.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.study.codeinterviewbot.handlers.CommandHandler;

import java.util.List;


@Component
public class CodeInterviewBot extends TelegramLongPollingBot {

    @Value("${telegram.bot.username}")
    private String botUsername;

    @Autowired
    private List<CommandHandler> commandHandlers;

    public CodeInterviewBot(@Value("${telegram.bot.token}") String botToken) {
        super(botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();

            for (CommandHandler handler : commandHandlers) {
                if (messageText.equals(handler.getCommand())) {
                    handler.handle(update);

                }

            }
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }
}