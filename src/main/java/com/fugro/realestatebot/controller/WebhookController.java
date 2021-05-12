package com.fugro.realestatebot.controller;

import com.fugro.realestatebot.bot.RealEstateWebhookBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
public class WebhookController {

    private final RealEstateWebhookBot webhookBot;

    @Autowired
    public WebhookController(RealEstateWebhookBot bot) {
        this.webhookBot = bot;
    }

    @PostMapping
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        return webhookBot.onWebhookUpdateReceived(update);
    }
}
