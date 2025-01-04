package ru.study.codeinterviewbot.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.study.codeinterviewbot.entity.User;
import ru.study.codeinterviewbot.repository.UserRepository;
import ru.study.codeinterviewbot.service.UserService;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        if (!userRepository.existsByChatId(user.getChatId())) {
            userRepository.save(user);
            log.info("User with chatId '{}' successfully saved to the database.", user.getChatId());
        } else {
            log.info("User with chatId '{}' already exists in the database.", user.getChatId());
        }
    }

    @Override
    public User getUserByChatId(Long chatId) {
        return userRepository.findUserByChatId(chatId).orElseThrow();
    }
}