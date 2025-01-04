package ru.study.codeinterviewbot.handlers.java;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.study.codeinterviewbot.entity.JavaSection;
import ru.study.codeinterviewbot.handlers.CommandHandler;
import ru.study.codeinterviewbot.service.JavaSectionService;

import static ru.study.codeinterviewbot.utils.KeyboardUtils.createKeyboard;

@Component
@RequiredArgsConstructor
public class JavaLanguageCommandHandler implements CommandHandler {

    private final JavaSectionService javaSectionService;

    @Override
    public SendMessage handle(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
        sendMessage.setText("Выберете интересующий вас раздел");

        sendMessage.setReplyMarkup(createKeyboard(
                javaSectionService.getJavaSections(),
                JavaSection::getName,
                section -> "/java_section:" + section.getId()
        ));

        return sendMessage;
    }

    @Override
    public String getCommand() {
        return "/java";
    }
}
