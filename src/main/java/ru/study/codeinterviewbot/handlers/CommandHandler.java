package ru.study.codeinterviewbot.handlers;

import org.hibernate.grammars.importsql.SqlScriptParser;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public interface CommandHandler  {
  void handle(String message,Long chatId);
  String getCommand();

}
