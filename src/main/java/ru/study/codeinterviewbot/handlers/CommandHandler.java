package ru.study.codeinterviewbot.handlers;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface CommandHandler {

    void handle(Update update);

    String getCommand();

}
