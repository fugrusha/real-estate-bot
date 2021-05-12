package com.fugro.realestatebot.service.impl;

import com.fugro.realestatebot.bot.RealEstateBot;
import com.fugro.realestatebot.service.SendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class SendMessageServiceImpl implements SendMessageService {

    private final RealEstateBot realEstateBot;

    @Autowired
    public SendMessageServiceImpl(RealEstateBot bot) {
        this.realEstateBot = bot;
    }

    @Override
    public void sendMessage(String chatId, String messageText) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(messageText);

        try {
            realEstateBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            //todo add logging to the project.
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessage(String chatId, String messageText, ReplyKeyboard replyKeyboard) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(messageText);
        sendMessage.setReplyMarkup(replyKeyboard);

        try {
            realEstateBot.execute(sendMessage);
        } catch (TelegramApiException e) {
            //todo add logging to the project.
            e.printStackTrace();
        }
    }

    @Override
    public SendMessage getMessage(String chatId, String messageText) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(messageText);
        return sendMessage;
    }

    @Override
    public SendMessage getMessage(String chatId, String messageText, ReplyKeyboard replyKeyboard) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(messageText);
        sendMessage.setReplyMarkup(replyKeyboard);
        return sendMessage;
    }

    @Override
    public EditMessageText getEditMessage(
            String chatId, Integer messageId, String text, InlineKeyboardMarkup keyboard) {
        EditMessageText message = new EditMessageText();
        message.setChatId(chatId);
        message.setMessageId(messageId);
        message.setText(text);
        message.setParseMode("HTML");
        message.setReplyMarkup(keyboard);

        return message;
    }

    @Override
    public EditMessageText getEditMessage(String chatId, Integer messageId, String text) {
        EditMessageText message = new EditMessageText();
        message.setChatId(chatId);
        message.setMessageId(messageId);
        message.setText(text);
        message.setParseMode("HTML");

        return message;
    }

    @Override
    public void sendAnswerCallbackQuery(String text, boolean alert, CallbackQuery callbackquery) {
        AnswerCallbackQuery answer = new AnswerCallbackQuery();
        answer.setCallbackQueryId(callbackquery.getId());
        answer.setShowAlert(alert);
        answer.setText(text);
        try {
            realEstateBot.execute(answer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AnswerCallbackQuery getAnswerCallbackQuery(String text, boolean alert, CallbackQuery callbackquery) {
        AnswerCallbackQuery answer = new AnswerCallbackQuery();
        answer.setCallbackQueryId(callbackquery.getId());
        answer.setShowAlert(alert);
        answer.setText(text);

        return answer;
    }
}
