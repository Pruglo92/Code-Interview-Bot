package ru.study.codeinterviewbot.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.study.codeinterviewbot.handlers.CommandHandler;
import ru.study.codeinterviewbot.model.MessageData;

import java.util.List;

import static java.util.Objects.nonNull;

@Slf4j
@Component
public class CodeInterviewBot extends TelegramLongPollingBot {

    @Value("${telegram.bot.username}")
    private String botUsername;

    @Autowired
    private List<CommandHandler> commandHandlers;

    public CodeInterviewBot(@Value("${telegram.bot.token}") String botToken) {
        super(botToken);
    }

    private Integer messageId = null;

    @Override
    public void onUpdateReceived(Update update) {
        var messageData = extractMessageData(update);
        if (nonNull(messageData)) {
            processCommandHandlers(messageData, update);
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    private MessageData extractMessageData(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            return new MessageData(update.getMessage().getText(), update.getMessage().getChatId());
        } else if (update.hasCallbackQuery()) {
            var callbackData = update.getCallbackQuery().getData().split(":")[0];
            var chatId = update.getCallbackQuery().getMessage().getChatId();
            return new MessageData(callbackData, chatId);
        }
        return null;
    }

    private void processCommandHandlers(MessageData data, Update update) {
        for (CommandHandler handler : commandHandlers) {
            if (data.command().equals(handler.getCommand())) {
                try {
                    deletePreviousMessage(data.chatId());

                    Message message = execute(handler.handle(update));
                    messageId = message.getMessageId();
                } catch (TelegramApiException e) {
                    log.warn("Error processing command: " + data.command(), e);
                }
            }
        }
    }

    private void deletePreviousMessage(Long chatId) throws TelegramApiException {
        if (nonNull(messageId)) {
            var deleteMessage = new DeleteMessage();
            deleteMessage.setChatId(chatId);
            deleteMessage.setMessageId(messageId);
            execute(deleteMessage);
        }
    }
}