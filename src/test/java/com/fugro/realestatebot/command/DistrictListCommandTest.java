package com.fugro.realestatebot.command;

import com.fugro.realestatebot.bot.KeyboardFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.ArrayList;

public class DistrictListCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return CommandName.DISTRICT_LIST.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return DistrictListCommand.DISTRICT_LIST_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new DistrictListCommand(sendMessageService, easyBaseClient, districtSubService);
    }

    @Override
    protected SendMessage createSendMessage(Long chaId) {
        SendMessage sendMessage = super.createSendMessage(chaId);

        sendMessage.setReplyMarkup(KeyboardFactory.getDistrictListKeyboard(new ArrayList<>(), new ArrayList<>()));

        return sendMessage;
    }
}
