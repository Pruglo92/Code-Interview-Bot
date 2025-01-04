package ru.study.codeinterviewbot.utils;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class KeyboardUtils {

    private KeyboardUtils() {
    }

    public static List<InlineKeyboardButton> createBackToStartButton() {
        InlineKeyboardButton backToStartButton = new InlineKeyboardButton();
        backToStartButton.setText("В начало");
        backToStartButton.setCallbackData("/start");

        List<InlineKeyboardButton> backRow = new ArrayList<>();
        backRow.add(backToStartButton);
        return backRow;
    }

    public static <T> InlineKeyboardMarkup createKeyboard(
            List<T> items,
            Function<T, String> textExtractor,
            Function<T, String> callbackDataExtractor
    ) {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (T item : items) {
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(textExtractor.apply(item));
            button.setCallbackData(callbackDataExtractor.apply(item));

            List<InlineKeyboardButton> row = new ArrayList<>();
            row.add(button);
            keyboard.add(row);
        }

        keyboard.add(createBackToStartButton());

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }
}