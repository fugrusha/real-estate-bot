package com.fugro.realestatebot.command;

import com.fugro.realestatebot.bot.BotUtils;
import com.fugro.realestatebot.bot.KeyboardFactory;
import com.fugro.realestatebot.domain.TelegramUser;
import com.fugro.realestatebot.service.SendMessageService;
import com.fugro.realestatebot.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

public class StartCommand implements Command {

    private final SendMessageService sendMessageService;
    private final TelegramUserService userService;

    public static final String START_MESSAGE = "Hello, I am Johnny. And I will help you to find a flat you want";

    public StartCommand(SendMessageService sendMessageService, TelegramUserService userService) {
        this.sendMessageService = sendMessageService;
        this.userService = userService;
    }

    @Override
    public BotApiMethod<?> execute(Update update) {
        String chatId = BotUtils.getChatId(update);

        TelegramUser user = userService.findByChatId(chatId);

        if (user == null) {
            user = new TelegramUser();
            user.setChatId(chatId);
        }

        user.setActive(true);
        userService.save(user);

        ReplyKeyboardMarkup menuKeyboard = KeyboardFactory.getMainMenuKeyboard();

        return sendMessageService.getMessage(chatId, START_MESSAGE, menuKeyboard);
    }
}
