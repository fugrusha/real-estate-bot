package com.fugro.realestatebot.callback;

import com.fugro.realestatebot.bot.BotUtils;
import com.fugro.realestatebot.service.DistrictSubService;
import com.fugro.realestatebot.service.SendMessageService;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

public class DeleteDistrictSubCallbackHandler implements CallbackHandler {

    private final SendMessageService sendMessageService;
    private final DistrictSubService districtSubService;

    public DeleteDistrictSubCallbackHandler(SendMessageService sendMessageService, DistrictSubService districtSubService) {
        this.sendMessageService = sendMessageService;
        this.districtSubService = districtSubService;
    }

    public static final String SUCCESS_DELETION_MESSAGE = "Subscription removed";
    public static final String ERROR_DELETION_MESSAGE = "Ooops! Subscription was not removed";

    @Override
    public CallbackType getCallbackType() {
        return CallbackType.DELETE_DISTRICT_SUB;
    }

    @Override
    public BotApiMethod<?> handleCallback(CallbackQuery callbackQuery) {
        String chatId = BotUtils.getChatId(callbackQuery);

        Integer districtId = Integer.parseInt(callbackQuery.getData().split("=")[1]);

        boolean isRemoved = districtSubService.deleteSub(chatId, districtId);

        if (isRemoved) {
             return sendMessageService.getMessage(chatId, SUCCESS_DELETION_MESSAGE);
        }

        return sendMessageService.getMessage(chatId, ERROR_DELETION_MESSAGE);
    }

    // todo update keyboard without deleted sub
}
