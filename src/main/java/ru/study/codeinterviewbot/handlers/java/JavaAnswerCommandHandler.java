package ru.study.codeinterviewbot.handlers.java;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.study.codeinterviewbot.entity.JavaAnswer;
import ru.study.codeinterviewbot.entity.JavaQuestion;
import ru.study.codeinterviewbot.entity.User;
import ru.study.codeinterviewbot.entity.UserJavaProgress;
import ru.study.codeinterviewbot.handlers.CommandHandler;
import ru.study.codeinterviewbot.service.JavaAnswerService;
import ru.study.codeinterviewbot.service.JavaQuestionService;
import ru.study.codeinterviewbot.service.UserJavaProgressService;
import ru.study.codeinterviewbot.service.UserService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JavaAnswerCommandHandler implements CommandHandler {

    private final JavaQuestionService javaQuestionService;
    private final JavaAnswerService javaAnswerService;
    private final UserJavaProgressService userJavaProgressService;
    private final UserService userService;

    @Override
    public SendMessage handle(Update update) {
        Long chatId = update.getCallbackQuery().getMessage().getChatId();
        User user = userService.getUserByChatId(chatId);
        String answerId = update.getCallbackQuery().getData().split(":")[1];
        JavaAnswer finalAnswer = javaAnswerService.getAnswerById(Long.valueOf(answerId));
        String sectionId = finalAnswer.getJavaQuestion().getJavaSection().getId().toString();
        userJavaProgressService.saveProgress(new UserJavaProgress(finalAnswer.getIsCorrect(), user,
                finalAnswer.getJavaQuestion().getJavaSection(), finalAnswer.getJavaQuestion()));
        List<JavaQuestion> questions = javaQuestionService.getUnansweredQuestionsBySection(sectionId, chatId);
        return javaQuestionService.handleQuestions(
                chatId,
                questions,
                userJavaProgressService.getUserProgressForSection(Long.valueOf(sectionId))
        );
    }

    @Override
    public String getCommand() {
        return "/java_answer";
    }
}