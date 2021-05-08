package com.fugro.realestatebot.command;

import com.fugro.realestatebot.bot.BotUtils;
import com.fugro.realestatebot.bot.KeyboardFactory;
import com.fugro.realestatebot.domain.DistrictSub;
import com.fugro.realestatebot.service.DistrictSubService;
import com.fugro.realestatebot.service.SendMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.util.List;

public class MySubsCommand implements Command {

    private final SendMessageService sendMessageService;
    private final DistrictSubService districtSubService;

    public MySubsCommand(SendMessageService sendMessageService, DistrictSubService districtSubService) {
        this.sendMessageService = sendMessageService;
        this.districtSubService = districtSubService;
    }

    public static final String NO_SUBS_MESSAGE = "You haven't subscribed to any district's updates";
    public static final String MY_SUBS_MESSAGE = "Your active subscriptions below";

    @Override
    public void execute(Update update) {
        String chatId = BotUtils.getChatId(update);

        List<DistrictSub> subs = districtSubService.getUserSubs(chatId);

        if (subs.isEmpty()) {
            sendMessageService.sendMessage(chatId, NO_SUBS_MESSAGE);
        } else {
            ReplyKeyboard keyboard = KeyboardFactory.getActiveSubsKeyboard(subs);
            sendMessageService.sendMessage(chatId, MY_SUBS_MESSAGE, keyboard);
        }
    }
}
