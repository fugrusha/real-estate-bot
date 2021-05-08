package com.fugro.realestatebot.command;

import com.fugro.realestatebot.bot.BotUtils;
import com.fugro.realestatebot.service.SendMessageService;
import com.fugro.realestatebot.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StatsCommand implements Command{

    private final TelegramUserService userService;
    private final SendMessageService sendMessageService;

    public final static String STAT_MESSAGE = "Active users %s.";

    public StatsCommand(SendMessageService sendMessageService, TelegramUserService userService) {
        this.sendMessageService = sendMessageService;
        this.userService = userService;
    }

    @Override
    public void execute(Update update) {
        int activeUserCount = userService.getAllActiveUsers().size();
        String chatId = BotUtils.getChatId(update);

        sendMessageService.sendMessage(chatId, String.format(STAT_MESSAGE, activeUserCount));

    }
}
