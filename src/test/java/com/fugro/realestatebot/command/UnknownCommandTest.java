package com.fugro.realestatebot.command;

public class UnknownCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return "sadfddsf";
    }

    @Override
    String getCommandMessage() {
        return UnknownCommand.MESSAGE;
    }

    @Override
    Command getCommand() {
        return new UnknownCommand(sendMessageService);
    }
}
