package com.fugro.realestatebot.command;

import com.fugro.realestatebot.service.SendMessageService;
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
        commandContainer = new CommandContainer(sendMessageService);
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
