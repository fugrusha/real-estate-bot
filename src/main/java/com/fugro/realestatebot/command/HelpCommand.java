package com.fugro.realestatebot.command;

import com.fugro.realestatebot.bot.BotUtils;
import com.fugro.realestatebot.service.SendMessageService;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public class HelpCommand implements Command {

    private final SendMessageService sendMessageService;

    public static final String MESSAGE = "Don't worry, I will help you!";

    public HelpCommand(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
    }

    @Override
    public BotApiMethod<?> execute(Update update) {
        String chatId = BotUtils.getChatId(update);

        return sendMessageService.getMessage(chatId, MESSAGE);
    }
}
