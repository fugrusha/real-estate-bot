package com.fugro.realestatebot.command;

import com.fugro.realestatebot.bot.RealEstateBot;
import com.fugro.realestatebot.client.EasyBaseClient;
import com.fugro.realestatebot.service.SendMessageService;
import com.fugro.realestatebot.service.TelegramUserService;
import com.fugro.realestatebot.service.impl.SendMessageServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class AbstractCommandTest {

    protected RealEstateBot realEstateBot = Mockito.mock(RealEstateBot.class);
    protected TelegramUserService userService = Mockito.mock(TelegramUserService.class);
    protected EasyBaseClient easyBaseClient = Mockito.mock(EasyBaseClient.class);
    protected SendMessageService sendMessageService = new SendMessageServiceImpl(realEstateBot);

    abstract String getCommandName();

    abstract String getCommandMessage();

    abstract Command getCommand();

    @Test
    public void shouldProperlyExecuteCommand() throws Exception {
        // given
        Long chatId = 1234L;

        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getChatId()).thenReturn(chatId);
        Mockito.when(message.getText()).thenReturn(getCommandName());

        Update update = new Update();
        update.setMessage(message);

        SendMessage sendMessage = createSendMessage(chatId);

        // when
        getCommand().execute(update);

        // then
        Mockito.verify(realEstateBot).execute(sendMessage);

    }

    protected SendMessage createSendMessage(Long chaId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chaId.toString());
        sendMessage.setText(getCommandMessage());
        sendMessage.enableHtml(true);
        return sendMessage;
    }
}
