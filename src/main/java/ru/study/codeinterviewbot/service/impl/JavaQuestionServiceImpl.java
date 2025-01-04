package ru.study.codeinterviewbot.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.study.codeinterviewbot.entity.JavaAnswer;
import ru.study.codeinterviewbot.entity.JavaQuestion;
import ru.study.codeinterviewbot.repository.JavaQuestionRepository;
import ru.study.codeinterviewbot.service.JavaQuestionService;
import ru.study.codeinterviewbot.utils.KeyboardUtils;

import java.util.List;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class JavaQuestionServiceImpl implements JavaQuestionService {

    private final JavaQuestionRepository javaQuestionRepository;

    @Override
    public List<JavaQuestion> getUnansweredQuestionsBySection(String sectionId, Long chatId) {
        return javaQuestionRepository.findQuestionsBySectionAndUserProgress(sectionId, chatId);
    }

    @Override
    public SendMessage handleQuestions(
            Long chatId,
            List<JavaQuestion> questions,
            String noQuestionsText
    ) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        if (!questions.isEmpty()) {
            Random random = new Random();
            JavaQuestion question = questions.get(random.nextInt(questions.size()));
            sendMessage.setText(question.getText());
            sendMessage.setReplyMarkup(KeyboardUtils.createKeyboard(
                    question.getJavaAnswer(),
                    JavaAnswer::getText,
                    answer -> "/java_answer:" + answer.getId()
            ));
        } else {
            sendMessage.setText(noQuestionsText);
        }
        return sendMessage;
    }
}