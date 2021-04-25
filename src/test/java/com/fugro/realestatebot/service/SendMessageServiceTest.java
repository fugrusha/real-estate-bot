package com.fugro.realestatebot.service;

import com.fugro.realestatebot.bot.RealEstateBot;
import com.fugro.realestatebot.service.impl.SendMessageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@DisplayName("Unit-level testing for SendMessageService")
public class SendMessageServiceTest {

    private SendMessageService sendMessageService;
    private RealEstateBot realEstateBot;

    @BeforeEach
    public void init() {
        this.realEstateBot = Mockito.mock(RealEstateBot.class);
        this.sendMessageService = new SendMessageServiceImpl(realEstateBot);
    }

    @Test
    public void shouldProperlySendMessage() throws Exception {
        // given
        String chatId = "test_chat_id";
        String message = "Some text";

        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(message);
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);

        // when
        sendMessageService.sendMessage(chatId, message);

        // then
        Mockito.verify(realEstateBot).execute(sendMessage);
    }
}
