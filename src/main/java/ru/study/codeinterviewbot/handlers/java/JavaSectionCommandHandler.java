package ru.study.codeinterviewbot.handlers.java;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.study.codeinterviewbot.entity.JavaQuestion;
import ru.study.codeinterviewbot.handlers.CommandHandler;
import ru.study.codeinterviewbot.service.JavaQuestionService;
import ru.study.codeinterviewbot.service.UserJavaProgressService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JavaSectionCommandHandler implements CommandHandler {

    private final JavaQuestionService javaQuestionService;
    private final UserJavaProgressService userJavaProgressService;

    @Override
    public SendMessage handle(Update update) {
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        String sectionId = update.getCallbackQuery().getData().split(":")[1];
        List<JavaQuestion> questions = javaQuestionService.getUnansweredQuestionsBySection(sectionId, chatId);
        return javaQuestionService.handleQuestions(
                chatId,
                questions,
                userJavaProgressService.getUserProgressForSection(Long.valueOf(sectionId))
        );
    }

    @Override
    public String getCommand() {
        return "/java_section";
    }
}