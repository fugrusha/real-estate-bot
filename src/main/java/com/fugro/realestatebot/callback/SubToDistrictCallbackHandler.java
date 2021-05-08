package com.fugro.realestatebot.callback;

import com.fugro.realestatebot.bot.BotUtils;
import com.fugro.realestatebot.domain.DistrictSub;
import com.fugro.realestatebot.service.DistrictSubService;
import com.fugro.realestatebot.service.SendMessageService;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.util.Optional;

public class SubToDistrictCallbackHandler implements CallbackHandler {

    private final SendMessageService sendMessageService;
    private final DistrictSubService districtSubService;

    public SubToDistrictCallbackHandler(SendMessageService sendMessageService, DistrictSubService districtSubService) {
        this.sendMessageService = sendMessageService;
        this.districtSubService = districtSubService;
    }

    public static final String SUCCESS_SUB_MESSAGE = "Great! Now you will receive updates for %s";
    public static final String DUPLICATE_SUB_MESSAGE = "Yoy! You have already subscribed to updates for %s";

    @Override
    public CallbackType getCallbackType() {
        return CallbackType.SUB_TO_DISTRICT;
    }

    @Override
    public void handleCallback(CallbackQuery callbackQuery) {
        String chatId = BotUtils.getChatId(callbackQuery);

        Integer districtId = Integer.parseInt(callbackQuery.getData().split("=")[1]);

        Optional<DistrictSub> sub = districtSubService.getSub(chatId, districtId);
        if (sub.isPresent()) {
            sendMessageService.sendMessage(chatId, String.format(DUPLICATE_SUB_MESSAGE, sub.get().getDistrictName()));
        } else {
            DistrictSub newSub = districtSubService.createSub(chatId, districtId);
            sendMessageService.sendMessage(chatId, String.format(SUCCESS_SUB_MESSAGE, newSub.getDistrictName()));
        }
    }
}
