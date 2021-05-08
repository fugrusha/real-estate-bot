package com.fugro.realestatebot.command;

import com.fugro.realestatebot.bot.BotUtils;
import com.fugro.realestatebot.service.SendMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class NoCommand implements Command {

    private final SendMessageService sendMessageService;

    public static final String NO_MESSAGE = "Я поддерживаю команды, начинающиеся со слеша(/).\n"
            + "Чтобы посмотреть список команд введите /help";

    public NoCommand(SendMessageService sendMessageService) {
        this.sendMessageService = sendMessageService;
    }

    @Override
    public void execute(Update update) {
        String chatId = BotUtils.getChatId(update);
        sendMessageService.sendMessage(chatId, NO_MESSAGE);
    }
}
