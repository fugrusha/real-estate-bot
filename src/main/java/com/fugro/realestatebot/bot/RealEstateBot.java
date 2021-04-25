package com.fugro.realestatebot.bot;

import com.fugro.realestatebot.command.CommandContainer;
import com.fugro.realestatebot.domain.TelegramUser;
import com.fugro.realestatebot.service.TelegramUserService;
import com.fugro.realestatebot.service.impl.SendMessageServiceImpl;
import com.fugro.realestatebot.service.impl.TelegramUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.fugro.realestatebot.command.CommandName.NO;

@Component
public class RealEstateBot extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    private static final String COMMAND_PREFIX = "/";

    private final CommandContainer commandContainer;

    @Autowired
    public RealEstateBot(TelegramUserService userService) {
        this.commandContainer = new CommandContainer(new SendMessageServiceImpl(this), userService);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();

                commandContainer.retrieveCommand(commandIdentifier).execute(update);
            } else {
                commandContainer.retrieveCommand(NO.getCommandName()).execute(update);
            }
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
}
