package com.fugro.realestatebot.command;

import com.fugro.realestatebot.service.SendMessageService;
import com.fugro.realestatebot.service.TelegramUserService;
import com.google.common.collect.ImmutableMap;

import static com.fugro.realestatebot.command.CommandName.*;

public class CommandContainer {

    private final ImmutableMap<String, Command> COMMAND_MAP;
    private final Command unknownCommand;

    public CommandContainer(SendMessageService sendMessageService, TelegramUserService userService) {
        COMMAND_MAP = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(sendMessageService, userService))
                .put(STOP.getCommandName(), new StopCommand(sendMessageService, userService))
                .put(STATS.getCommandName(), new StatsCommand(sendMessageService, userService))
                .put(HELP.getCommandName(), new HelpCommand(sendMessageService))
                .put(NO.getCommandName(), new NoCommand(sendMessageService))
                .build();

        unknownCommand = new UnknownCommand(sendMessageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return COMMAND_MAP.getOrDefault(commandIdentifier, unknownCommand);
    }
}
