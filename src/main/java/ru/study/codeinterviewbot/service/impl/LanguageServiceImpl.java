package ru.study.codeinterviewbot.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.study.codeinterviewbot.entity.Language;
import ru.study.codeinterviewbot.repository.LanguageRepository;
import ru.study.codeinterviewbot.service.LanguageService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;

    @Override
    public List<Language> getLanguages() {
        return languageRepository.findAll();
    }
}
