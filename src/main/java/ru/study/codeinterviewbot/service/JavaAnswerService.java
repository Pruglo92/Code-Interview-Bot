package ru.study.codeinterviewbot.service;

import ru.study.codeinterviewbot.entity.JavaAnswer;

public interface JavaAnswerService {
    JavaAnswer getAnswerById(Long answerId);
}