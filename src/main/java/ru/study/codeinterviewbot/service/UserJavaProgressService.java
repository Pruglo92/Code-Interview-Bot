package ru.study.codeinterviewbot.service;

import ru.study.codeinterviewbot.entity.UserJavaProgress;

public interface UserJavaProgressService {
    void saveProgress(UserJavaProgress progress);

    String getUserProgressForSection(Long sectionId);
}