package com.fugro.realestatebot.callback;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

public interface CallbackHandler {

    CallbackType getCallbackType();

    BotApiMethod<?> handleCallback(CallbackQuery callbackQuery);
}
