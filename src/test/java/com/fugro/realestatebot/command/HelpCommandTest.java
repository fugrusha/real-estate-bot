package com.fugro.realestatebot.command;

public class HelpCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return CommandName.HELP.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return HelpCommand.MESSAGE;
    }

    @Override
    Command getCommand() {
        return new HelpCommand(sendMessageService);
    }
}
