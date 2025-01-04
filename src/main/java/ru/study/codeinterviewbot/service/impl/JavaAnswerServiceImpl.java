package ru.study.codeinterviewbot.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.study.codeinterviewbot.entity.JavaAnswer;
import ru.study.codeinterviewbot.repository.JavaAnswerRepository;
import ru.study.codeinterviewbot.service.JavaAnswerService;

@Service
@Transactional
@RequiredArgsConstructor
public class JavaAnswerServiceImpl implements JavaAnswerService {

    private final JavaAnswerRepository javaAnswerRepository;

    @Override
    public JavaAnswer getAnswerById(Long answerId) {
        return javaAnswerRepository.findById(answerId).orElseThrow();
    }
}