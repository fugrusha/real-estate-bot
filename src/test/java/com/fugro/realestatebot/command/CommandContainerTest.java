package com.fugro.realestatebot.command;

import com.fugro.realestatebot.client.EasyBaseClient;
import com.fugro.realestatebot.service.DistrictSubService;
import com.fugro.realestatebot.service.SendMessageService;
import com.fugro.realestatebot.service.TelegramUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

public class CommandContainerTest {

    private CommandContainer commandContainer;

    @BeforeEach
    public void init() {
        SendMessageService sendMessageService = Mockito.mock(SendMessageService.class);
        TelegramUserService userService = Mockito.mock(TelegramUserService.class);
        EasyBaseClient easyBaseClient = Mockito.mock(EasyBaseClient.class);
        DistrictSubService districtSubService = Mockito.mock(DistrictSubService.class);
        commandContainer = new CommandContainer(sendMessageService, userService, easyBaseClient, districtSubService);
    }

    @Test
    public void shouldGetAllTheExistingCommands() {
        // when - then
        Arrays.stream(CommandName.values())
                .forEach(commandName -> {
                    Command command = commandContainer.retrieveCommand(commandName.getCommandName());
                    Assertions.assertNotEquals(UnknownCommand.class, command.getClass());
                });
    }

    @Test
    public void shouldReturnUnknownCommand() {
        // given
        String unknownCommand = "asdfghj";

        // when
        Command command = commandContainer.retrieveCommand(unknownCommand);

        // then
        Assertions.assertEquals(UnknownCommand.class, command.getClass());
    }
}
