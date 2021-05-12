package com.fugro.realestatebot.command;

public enum CommandName {

    START("/start"),
    STOP("\uD83D\uDED1 Остановить бота"),
    HELP("❓ Помощь"),
    STATS("\uD83C\uDF96 Статистика"),
    MY_SUBS("\uD83D\uDCF0 Мои подписки"),
    DISTRICT_LIST("\uD83C\uDFD9 Список районов"),
    NO("no_command");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
