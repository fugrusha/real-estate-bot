package com.fugro.realestatebot.bot;

import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

public class BotUtils {

    /**
     * Retrieve chatId from {@link Update} object.
     *
     * @param update provided {@link Update}
     * @return chatID from the provided {@link Update} object.
     */
    public static String getChatId(Update update) {
        return update.getMessage().getChatId().toString();
    }

    /**
     * Retrieve text of the message from {@link Update} object.
     *
     * @param update provided {@link Update}
     * @return the text of the message from the provided {@link Update} object.
     */
    public static String getMessage(Update update) {
        return update.getMessage().getText();
    }

    /**
     * Retrieve chatId from {@link CallbackQuery} object.
     *
     * @param callbackQuery provided {@link CallbackQuery}
     * @return chatID from the provided {@link CallbackQuery} object.
     */
    public static String getChatId(CallbackQuery callbackQuery) {
        return callbackQuery.getMessage().getChatId().toString();
    }
}
