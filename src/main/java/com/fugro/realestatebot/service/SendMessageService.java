package com.fugro.realestatebot.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public interface SendMessageService {

    void sendMessage(String chatId, String messageText);

    void sendMessage(String chatId, String messageText, ReplyKeyboard replyKeyboard);
}
