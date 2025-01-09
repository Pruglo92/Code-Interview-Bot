package ru.study.codeinterviewbot.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.study.codeinterviewbot.entity.JavaAnswer;
import ru.study.codeinterviewbot.entity.JavaQuestion;
import ru.study.codeinterviewbot.repository.JavaQuestionRepository;
import ru.study.codeinterviewbot.service.JavaQuestionService;
import ru.study.codeinterviewbot.service.UserJavaProgressService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static ru.study.codeinterviewbot.utils.KeyboardUtils.createBackToStartButton;
import static ru.study.codeinterviewbot.utils.KeyboardUtils.createKeyboard;

@Service
@Transactional
@RequiredArgsConstructor
public class JavaQuestionServiceImpl implements JavaQuestionService {

    private final JavaQuestionRepository javaQuestionRepository;
    private final UserJavaProgressService userJavaProgressService;

    @Override
    public List<JavaQuestion> getUnansweredQuestionsBySection(String sectionId, Long chatId) {
        return javaQuestionRepository.findQuestionsBySectionAndUserProgress(sectionId, chatId);
    }

    @Override
    public SendMessage handleQuestions(
            Long chatId,
            List<JavaQuestion> questions,
            String sectionId
    ) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);

        if (!questions.isEmpty()) {
            Random random = new Random();
            JavaQuestion question = questions.get(random.nextInt(questions.size()));
            sendMessage.setText(question.getText());
            sendMessage.setReplyMarkup(createKeyboard(
                    question.getJavaAnswer(),
                    JavaAnswer::getText,
                    answer -> "/java_answer:" + answer.getId()
            ));
        } else {
            sendMessage.setText(userJavaProgressService.getUserProgressForSection(Long.valueOf(sectionId)));
            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
            InlineKeyboardButton backToStartButton = new InlineKeyboardButton();
            backToStartButton.setText("Сбросить прогресс и в начало");
            backToStartButton.setCallbackData("/clear_start:" + sectionId);

            List<InlineKeyboardButton> backRow = new ArrayList<>();
            backRow.add(backToStartButton);
            keyboard.add(backRow);
            keyboard.add(createBackToStartButton());

            inlineKeyboardMarkup.setKeyboard(keyboard);
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        }
        return sendMessage;
    }
}