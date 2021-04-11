package com.fugro.realestatebot.command;

import com.fugro.realestatebot.service.SendMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StopCommand implements Command {

    private SendMessageService sendMessageService;

    private static final String STOP_MESSAGE = "Okay. I will delete all your data and subscriptions";

    public StopCommand(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();

        sendMessageService.sendMessage(chatId, STOP_MESSAGE);
    }
}
