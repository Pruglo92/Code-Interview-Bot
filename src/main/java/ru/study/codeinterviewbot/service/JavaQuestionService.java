package ru.study.codeinterviewbot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.study.codeinterviewbot.entity.JavaQuestion;

import java.util.List;

public interface JavaQuestionService {
    List<JavaQuestion> getUnansweredQuestionsBySection(String sectionId, Long chatId);

    SendMessage handleQuestions(
            Long chatId,
            List<JavaQuestion> questions,
            String noQuestionsText
    );
}