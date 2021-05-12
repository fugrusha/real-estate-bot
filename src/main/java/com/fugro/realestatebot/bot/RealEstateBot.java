package com.fugro.realestatebot.bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface RealEstateBot {

    void execute(SendMessage sendMessage) throws TelegramApiException;
}
