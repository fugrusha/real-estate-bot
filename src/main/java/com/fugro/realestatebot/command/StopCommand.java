package com.fugro.realestatebot.command;

import com.fugro.realestatebot.bot.BotUtils;
import com.fugro.realestatebot.domain.TelegramUser;
import com.fugro.realestatebot.service.DistrictSubService;
import com.fugro.realestatebot.service.SendMessageService;
import com.fugro.realestatebot.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StopCommand implements Command {

    private final SendMessageService sendMessageService;
    private final TelegramUserService userService;
    private final DistrictSubService districtSubService;

    public static final String STOP_MESSAGE = "Okay. I will delete all your data and subscriptions";

    public StopCommand(SendMessageService sendMessageService,
                       TelegramUserService userService,
                       DistrictSubService subService
    ) {
        this.sendMessageService = sendMessageService;
        this.userService = userService;
        this.districtSubService = subService;
    }

    @Override
    public BotApiMethod<?> execute(Update update) {
        String chatId = BotUtils.getChatId(update);

        TelegramUser user = userService.findByChatId(chatId);
        if (user != null) {
            user.setActive(false);
            userService.save(user);

            districtSubService.deleteAllSubs(chatId);
        }

        return sendMessageService.getMessage(chatId, STOP_MESSAGE);
    }
}
