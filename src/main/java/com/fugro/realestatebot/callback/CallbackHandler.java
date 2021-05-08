package com.fugro.realestatebot.callback;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

public interface CallbackHandler {

    CallbackType getCallbackType();

    void handleCallback(CallbackQuery callbackQuery);
}
