package com.fugro.realestatebot.command;

import com.fugro.realestatebot.domain.TelegramUser;
import com.fugro.realestatebot.service.SendMessageService;
import com.fugro.realestatebot.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartCommand implements Command {

    private final SendMessageService sendMessageService;
    private final TelegramUserService userService;

    public static final String START_MESSAGE = "Hello, I am Johnny. And I will help you to find a flat you want";

    public StartCommand(SendMessageService sendMessageService, TelegramUserService userService) {
        this.sendMessageService = sendMessageService;
        this.userService = userService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();

        TelegramUser user = userService.findByChatId(chatId);

        if (user == null) {
            user = new TelegramUser();
            user.setChatId(chatId);
        }

        user.setActive(true);
        userService.save(user);

        sendMessageService.sendMessage(chatId, START_MESSAGE);
    }
}
