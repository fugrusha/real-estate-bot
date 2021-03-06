package com.fugro.realestatebot.command;

import com.fugro.realestatebot.bot.KeyboardFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class StartCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return CommandName.START.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return StartCommand.START_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new StartCommand(sendMessageService, userService);
    }

    @Override
    protected SendMessage createSendMessage(Long chaId) {
        SendMessage sendMessage = super.createSendMessage(chaId);

        sendMessage.setReplyMarkup(KeyboardFactory.getMainMenuKeyboard());

        return sendMessage;
    }
}
