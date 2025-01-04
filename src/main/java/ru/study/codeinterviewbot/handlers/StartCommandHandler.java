package ru.study.codeinterviewbot.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.study.codeinterviewbot.entity.Language;
import ru.study.codeinterviewbot.entity.User;
import ru.study.codeinterviewbot.service.LanguageService;
import ru.study.codeinterviewbot.service.UserService;

import java.util.Objects;

import static ru.study.codeinterviewbot.utils.KeyboardUtils.createKeyboard;

@Component
@RequiredArgsConstructor
public class StartCommandHandler implements CommandHandler {

    private final LanguageService languageService;
    private final UserService userService;

    @Override
    public SendMessage handle(Update update) {
        Long chatId = null;
        String userName = null;
        if (update.hasMessage() && update.getMessage().hasText()) {
            chatId = update.getMessage().getChatId();
            userName = update.getMessage().getFrom().getUserName();
        } else if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId();
            userName = update.getCallbackQuery().getFrom().getUserName();
        }
        userService.saveUser(new User(chatId, userName, null));
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(Objects.requireNonNull(chatId));
        sendMessage.setText("Выберите язык программирования:");

        sendMessage.setReplyMarkup(createKeyboard(
                languageService.getLanguages(),
                Language::getName,
                language -> language.getCommand().getValue()
        ));

        return sendMessage;
    }

    @Override
    public String getCommand() {
        return "/start";
    }
}