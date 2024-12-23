package ru.study.codeinterviewbot.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.study.codeinterviewbot.entity.Language;
import ru.study.codeinterviewbot.service.LanguageService;
import ru.study.codeinterviewbot.utils.MessageSender;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class StartCommandHandler implements CommandHandler {

    private final LanguageService languageService;

    private final MessageSender messageSender;

    @Override
    public void handle(Update update) {
        // Создаем сообщение
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setText("Выберите язык программирования:");

        // Создаем клавиатуру с кнопками
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        // Добавляем кнопки для каждого языка
        for (Language language : languageService.getLanguages()) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(language.getName()); // Название кнопки
            button.setCallbackData(language.getCommand().getValue()); // Данные для обработки нажатия

            // Создаем строку клавиатуры с кнопкой
            List<InlineKeyboardButton> row = new ArrayList<>();
            row.add(button);
            keyboard.add(row); // Добавляем строку в клавиатуру
        }

        // Устанавливаем клавиатуру для сообщения
        inlineKeyboardMarkup.setKeyboard(keyboard);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        try {
            // Отправляем сообщение
            messageSender.send(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace(); // Логируем ошибку
        }
    }

    @Override
    public String getCommand() {
        return "/start";
    }
}