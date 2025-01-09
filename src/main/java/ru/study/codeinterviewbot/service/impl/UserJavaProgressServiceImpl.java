package ru.study.codeinterviewbot.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.study.codeinterviewbot.entity.UserJavaProgress;
import ru.study.codeinterviewbot.repository.UserJavaProgressRepository;
import ru.study.codeinterviewbot.service.UserJavaProgressService;
import ru.study.codeinterviewbot.service.UserService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserJavaProgressServiceImpl implements UserJavaProgressService {

    private final UserJavaProgressRepository userJavaProgressRepository;
    private final UserService userService;

    @Override
    public void saveProgress(UserJavaProgress progress) {
        userJavaProgressRepository.save(progress);
    }

    @Override
    public String getUserProgressForSection(Long sectionId) {
        List<UserJavaProgress> progresses = userJavaProgressRepository.findAllByJavaSectionId(sectionId);

        long totalAnswers = progresses.size();
        long correctAnswers = progresses.stream()
                .filter(UserJavaProgress::getIsCorrect)
                .count();

        double correctPercentage = (double) correctAnswers / totalAnswers * 100;

        return String.format("Всего ответов: %d\nПравильных ответов: %d\nПроцент правильных: %.2f%%",
                totalAnswers, correctAnswers, correctPercentage);
    }

    @Override
    public void clearUserProgressForSection(Long chatId, String sectionId) {
        var userId = userService.getUserByChatId(chatId).getId();
        userJavaProgressRepository.deleteAllByUserIdAndJavaSectionId(userId, Long.valueOf(sectionId));
    }
}