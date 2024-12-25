package ru.study.codeinterviewbot.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.study.codeinterviewbot.entity.Language;
import ru.study.codeinterviewbot.service.LanguageService;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StartCommandHandler implements CommandHandler {

    private final LanguageService languageService;

    @Override
    public SendMessage handle(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setText("Выберите язык программирования:");

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (Language language : languageService.getLanguages()) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(language.getName());
            button.setCallbackData(language.getCommand().getValue());

            List<InlineKeyboardButton> row = new ArrayList<>();
            row.add(button);
            keyboard.add(row);
        }

        inlineKeyboardMarkup.setKeyboard(keyboard);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        return sendMessage;
    }

    @Override
    public String getCommand() {
        return "/start";
    }
}