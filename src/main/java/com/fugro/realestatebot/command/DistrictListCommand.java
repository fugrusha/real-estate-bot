package com.fugro.realestatebot.command;

import com.fugro.realestatebot.bot.BotUtils;
import com.fugro.realestatebot.bot.KeyboardFactory;
import com.fugro.realestatebot.client.EasyBaseClient;
import com.fugro.realestatebot.client.dto.DistrictDTO;
import com.fugro.realestatebot.domain.DistrictSub;
import com.fugro.realestatebot.service.DistrictSubService;
import com.fugro.realestatebot.service.SendMessageService;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.List;

public class DistrictListCommand implements Command {

    private final SendMessageService sendMessageService;
    private final EasyBaseClient easyBaseClient;
    private final DistrictSubService districtSubService;

    public static final String DISTRICT_LIST_MESSAGE = "Please, tap on district for which you want to get updates"
            + "\n\u2705 - means that you have already subscribed";

    public DistrictListCommand(SendMessageService sendMessageService,
                               EasyBaseClient easyBaseClient,
                               DistrictSubService districtSubService
    ) {
        this.sendMessageService = sendMessageService;
        this.easyBaseClient = easyBaseClient;
        this.districtSubService = districtSubService;
    }

    @Override
    public BotApiMethod<?> execute(Update update) {
        String chatId = BotUtils.getChatId(update);

        List<DistrictDTO> districts = easyBaseClient.getAllDistricts();
        List<DistrictSub> userSubs = districtSubService.getUserSubs(chatId);

        InlineKeyboardMarkup keyboard = KeyboardFactory.getDistrictListKeyboard(districts, userSubs);

        return sendMessageService.getMessage(chatId, DISTRICT_LIST_MESSAGE, keyboard);
    }
}
