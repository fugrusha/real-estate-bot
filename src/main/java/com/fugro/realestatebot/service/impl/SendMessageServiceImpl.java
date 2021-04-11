package com.fugro.realestatebot.service.impl;

import com.fugro.realestatebot.bot.RealEstateBot;
import com.fugro.realestatebot.service.SendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class SendMessageServiceImpl implements SendMessageService {

    private RealEstateBot realEstateBot;

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
}
