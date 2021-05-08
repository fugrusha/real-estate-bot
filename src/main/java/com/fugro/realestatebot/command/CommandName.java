package com.fugro.realestatebot.command;

public enum CommandName {

    START("/start"),
    STOP("/stop"),
    HELP("/help"),
    STATS("/stats"),
    MY_SUBS("/mysubs"),
    DISTRICT_LIST("/districtlist"),
    NO("no_command");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
