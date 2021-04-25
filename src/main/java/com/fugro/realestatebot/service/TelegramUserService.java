package com.fugro.realestatebot.service;

import com.fugro.realestatebot.domain.TelegramUser;

import java.util.List;

public interface TelegramUserService {

    void save(TelegramUser telegramUser);

    List<TelegramUser> getAllActiveUsers();

    TelegramUser findByChatId(String chatId);
}
