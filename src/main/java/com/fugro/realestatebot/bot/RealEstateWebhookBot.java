package com.fugro.realestatebot.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class RealEstateWebhookBot extends TelegramWebhookBot implements RealEstateBot {

    @Lazy
    @Autowired
    private BotFacade botFacade;

    @Value("${bot.webhook.path}")
    private String webhookPath;

    @Value("${bot.username}")
    private String botUsername;

    @Value("${bot.token}")
    private String botToken;

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return botFacade.processUpdate(update);
    }

    @Override
    public String getBotPath() {
        return webhookPath;
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
