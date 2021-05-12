package com.fugro.realestatebot.bot;

import com.fugro.realestatebot.callback.CallbackFacade;
import com.fugro.realestatebot.client.EasyBaseClient;
import com.fugro.realestatebot.command.CommandContainer;
import com.fugro.realestatebot.service.DistrictSubService;
import com.fugro.realestatebot.service.TelegramUserService;
import com.fugro.realestatebot.service.impl.SendMessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.fugro.realestatebot.command.CommandName.NO;

@Component
public class BotFacade {

    private static final String COMMAND_PREFIX = "/";

    private final CommandContainer commandContainer;

    private final CallbackFacade callbackFacade;

    @Autowired
    public BotFacade(TelegramUserService userService,
                     EasyBaseClient easyBaseClient,
                     DistrictSubService districtSubService,
                     RealEstateBot realEstateBot
    ) {
        super();
        this.commandContainer = new CommandContainer(
                new SendMessageServiceImpl(realEstateBot),
                userService,
                easyBaseClient,
                districtSubService
        );

        this.callbackFacade = new CallbackFacade(
                new SendMessageServiceImpl(realEstateBot),
                userService,
                easyBaseClient,
                districtSubService
        );
    }


    public BotApiMethod<?> processUpdate(Update update) {
        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            return callbackFacade.processCallback(callbackQuery);
        }

        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();

                return commandContainer.retrieveCommand(commandIdentifier).execute(update);
            } else {
                return commandContainer.retrieveCommand(NO.getCommandName()).execute(update);
            }
        }

        return null;
    }
}
