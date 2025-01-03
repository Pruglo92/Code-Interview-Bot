package ru.study.codeinterviewbot.handlers.java;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.study.codeinterviewbot.entity.JavaSection;
import ru.study.codeinterviewbot.handlers.CommandHandler;
import ru.study.codeinterviewbot.service.JavaSectionService;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JavaLanguageCommandHandler implements CommandHandler {

    private final JavaSectionService javaSectionService;

    @Override
    public SendMessage handle(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
        sendMessage.setText("Выберете интересующий вас раздел");

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (JavaSection javaSection : javaSectionService.getJavaSections()) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(javaSection.getName());
            button.setCallbackData("/java_section_" + javaSection.getId());

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
        return "/java";
    }
}
