package ru.study.codeinterviewbot.service;

import ru.study.codeinterviewbot.entity.User;

public interface UserService {
    void saveUser(User user);

    User getUserByChatId(Long userId);
}