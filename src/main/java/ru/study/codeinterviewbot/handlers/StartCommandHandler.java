package ru.study.codeinterviewbot.handlers;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.study.codeinterviewbot.service.LanguageService;

@RequiredArgsConstructor
public class StartCommandHandler implements CommandHandler {
    private final LanguageService languageService;


    @Override
    public void handle(String message, Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(languageService.getLanguages().toString());
    }

    @Override
    public String getCommand() {

        return "/start";
    }
}
