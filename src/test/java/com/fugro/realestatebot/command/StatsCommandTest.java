package com.fugro.realestatebot.command;

public class StatsCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return CommandName.STATS.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return String.format(StatsCommand.STAT_MESSAGE, 0);
    }

    @Override
    Command getCommand() {
        return new StatsCommand(sendMessageService, userService);
    }
}
