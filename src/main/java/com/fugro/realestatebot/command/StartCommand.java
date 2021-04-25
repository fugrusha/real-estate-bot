package com.fugro.realestatebot.command;

import com.fugro.realestatebot.service.SendMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartCommand implements Command {

    private final SendMessageService sendMessageService;

    public static final String START_MESSAGE = "Hello, I am Johnny. And I will help you to find a flat you want";

    public StartCommand(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();

        sendMessageService.sendMessage(chatId, START_MESSAGE);
    }
}
