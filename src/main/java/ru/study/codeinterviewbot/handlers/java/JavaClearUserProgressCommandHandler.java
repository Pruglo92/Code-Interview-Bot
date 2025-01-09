package ru.study.codeinterviewbot.handlers.java;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.study.codeinterviewbot.handlers.CommandHandler;
import ru.study.codeinterviewbot.handlers.StartCommandHandler;
import ru.study.codeinterviewbot.service.UserJavaProgressService;

@Component
@RequiredArgsConstructor
public class JavaClearUserProgressCommandHandler implements CommandHandler {

    private final UserJavaProgressService userJavaProgressService;
    private final StartCommandHandler startCommandHandler;

    @Override
    public SendMessage handle(Update update) {
        var chatId = update.getCallbackQuery().getMessage().getChatId();
        var sectionId = update.getCallbackQuery().getData().split(":")[1];
        userJavaProgressService.clearUserProgressForSection(chatId, sectionId);
        return startCommandHandler.handle(update);
    }

    @Override
    public String getCommand() {
        return "/clear_start";
    }
}