package com.fugro.realestatebot.callback;

import com.fugro.realestatebot.client.EasyBaseClient;
import com.fugro.realestatebot.service.DistrictSubService;
import com.fugro.realestatebot.service.SendMessageService;
import com.fugro.realestatebot.service.TelegramUserService;
import com.google.common.collect.ImmutableMap;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import static com.fugro.realestatebot.callback.CallbackType.DELETE_DISTRICT_SUB;
import static com.fugro.realestatebot.callback.CallbackType.SUB_TO_DISTRICT;

public class CallbackFacade {

    private final ImmutableMap<CallbackType, CallbackHandler> HANDLERS_MAP;

    public CallbackFacade(SendMessageService sendMessageService, TelegramUserService userService,
                          EasyBaseClient easyBaseClient, DistrictSubService districtSubService) {
        HANDLERS_MAP = ImmutableMap.<CallbackType, CallbackHandler>builder()
                .put(SUB_TO_DISTRICT, new SubToDistrictCallbackHandler(sendMessageService, districtSubService))
                .put(DELETE_DISTRICT_SUB, new DeleteDistrictSubCallbackHandler(sendMessageService, districtSubService))
                .build();
    }

    public BotApiMethod<?> processCallback(CallbackQuery callbackQuery) {
        CallbackType usersQueryType = CallbackType.valueOf(callbackQuery.getData().split("=")[0]);

        CallbackHandler currentHandler = findCallbackHandler(usersQueryType);

        return currentHandler.handleCallback(callbackQuery);
    }

    private CallbackHandler findCallbackHandler(CallbackType callbackType) {
        return HANDLERS_MAP.get(callbackType);
    }
}
