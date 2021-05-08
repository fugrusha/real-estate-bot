package com.fugro.realestatebot.command;

import com.fugro.realestatebot.bot.BotUtils;
import com.fugro.realestatebot.bot.KeyboardFactory;
import com.fugro.realestatebot.client.EasyBaseClient;
import com.fugro.realestatebot.client.dto.DistrictDTO;
import com.fugro.realestatebot.service.SendMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.util.List;

public class DistrictListCommand implements Command {

    private final SendMessageService sendMessageService;
    private final EasyBaseClient easyBaseClient;

    public static final String MESSAGE = "Please, choose districts for which you want to get updates";

    public DistrictListCommand(SendMessageService sendMessageService, EasyBaseClient easyBaseClient) {
        this.sendMessageService = sendMessageService;
        this.easyBaseClient = easyBaseClient;
    }

    @Override
    public void execute(Update update) {
        String chatId = BotUtils.getChatId(update);

        List<DistrictDTO> districts = easyBaseClient.getAllDistricts();

        ReplyKeyboard keyboard = KeyboardFactory.getDistrictListKeyboard(districts);

        sendMessageService.sendMessage(chatId, MESSAGE, keyboard);
    }
}
