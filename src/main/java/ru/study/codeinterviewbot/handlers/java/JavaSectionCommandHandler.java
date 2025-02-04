package ru.study.codeinterviewbot.handlers.java;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.study.codeinterviewbot.handlers.CommandHandler;
import ru.study.codeinterviewbot.service.JavaQuestionService;

@Component
@RequiredArgsConstructor
public class JavaSectionCommandHandler implements CommandHandler {

    private final JavaQuestionService javaQuestionService;

    @Override
    public SendMessage handle(Update update) {
        var chatId = update.getCallbackQuery().getMessage().getChatId();
        var sectionId = update.getCallbackQuery().getData().split(":")[1];
        var questions = javaQuestionService.getUnansweredQuestionsBySection(sectionId, chatId);
        return javaQuestionService.handleQuestions(chatId, questions, sectionId);
    }

    @Override
    public String getCommand() {
        return "/java_section";
    }
}