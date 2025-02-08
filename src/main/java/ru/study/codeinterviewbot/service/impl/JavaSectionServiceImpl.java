package ru.study.codeinterviewbot.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.study.codeinterviewbot.entity.JavaSection;
import ru.study.codeinterviewbot.repository.JavaSectionRepository;
import ru.study.codeinterviewbot.service.JavaSectionService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class JavaSectionServiceImpl implements JavaSectionService {

    private final JavaSectionRepository javaSectionRepository;

    @Override
    public List<JavaSection> getJavaSections() {
        return javaSectionRepository.findAll();
    }
}
