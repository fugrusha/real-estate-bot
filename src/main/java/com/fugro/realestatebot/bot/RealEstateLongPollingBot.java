package com.fugro.realestatebot.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

//@Component
@Slf4j
public class RealEstateLongPollingBot extends TelegramLongPollingBot implements RealEstateBot {

    @Lazy
    @Autowired
    private BotFacade botFacade;

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    @Override
    public void onUpdateReceived(Update update) {
        BotApiMethod<?> response = botFacade.processUpdate(update);

        try {
            execute(response);
        } catch (TelegramApiException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void execute(SendMessage sendMessage) throws TelegramApiException {
        super.execute(sendMessage);
    }

    @Override
    public void execute(AnswerCallbackQuery answerCallbackQuery) throws TelegramApiException {
        super.execute(answerCallbackQuery);
    }
}
