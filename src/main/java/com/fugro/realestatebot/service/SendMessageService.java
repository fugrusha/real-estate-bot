package com.fugro.realestatebot.service;

import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public interface SendMessageService {

    void sendMessage(String chatId, String messageText);

    void sendMessage(String chatId, String messageText, ReplyKeyboard replyKeyboard);

    SendMessage getMessage(String chatId, String messageText);

    SendMessage getMessage(String chatId, String messageText, ReplyKeyboard replyKeyboard);

    EditMessageText getEditMessage(String chatId, Integer messageId, String text, InlineKeyboardMarkup keyboard);

    EditMessageText getEditMessage(String chatId, Integer messageId, String text);

    void sendAnswerCallbackQuery(String text, boolean alert, CallbackQuery callbackquery);

    AnswerCallbackQuery getAnswerCallbackQuery(String text, boolean alert, CallbackQuery callbackquery);
}
