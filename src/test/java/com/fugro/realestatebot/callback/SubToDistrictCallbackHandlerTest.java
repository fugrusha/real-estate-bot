package com.fugro.realestatebot.callback;

import com.fugro.realestatebot.bot.RealEstateBot;
import com.fugro.realestatebot.client.EasyBaseClient;
import com.fugro.realestatebot.domain.DistrictSub;
import com.fugro.realestatebot.service.DistrictSubService;
import com.fugro.realestatebot.service.SendMessageService;
import com.fugro.realestatebot.service.impl.SendMessageServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Optional;

import static com.fugro.realestatebot.callback.SubToDistrictCallbackHandler.DUPLICATE_SUB_MESSAGE;
import static com.fugro.realestatebot.callback.SubToDistrictCallbackHandler.SUCCESS_SUB_MESSAGE;
import static com.fugro.realestatebot.command.DistrictListCommand.DISTRICT_LIST_MESSAGE;

public class SubToDistrictCallbackHandlerTest {

    private final RealEstateBot realEstateBot = Mockito.mock(RealEstateBot.class);
    private final DistrictSubService districtSubService = Mockito.mock(DistrictSubService.class);
    private final EasyBaseClient easyBaseClient = Mockito.mock(EasyBaseClient.class);
    private final SendMessageService sendMessageService = new SendMessageServiceImpl(realEstateBot);

    private final int DISTRICT_ID = 2;
    private final String CHAT_ID = "1234";
    private final String CALLBACK_ID = "callbackId";

    private CallbackQuery callbackQuery;
    private DistrictSub sub;
    private SubToDistrictCallbackHandler callbackHandler;

    @BeforeEach
    void setUp() {
        Message message = Mockito.mock(Message.class);
        callbackQuery = Mockito.mock(CallbackQuery.class);
        Mockito.when(callbackQuery.getMessage()).thenReturn(message);
        Mockito.when(callbackQuery.getMessage().getChatId()).thenReturn(Long.parseLong(CHAT_ID));
        Mockito.when(callbackQuery.getData()).thenReturn("SUB_TO_DISTRICT=2");
        Mockito.when(callbackQuery.getId()).thenReturn(CALLBACK_ID);

        sub = new DistrictSub();
        sub.setDistrictId(DISTRICT_ID);
        sub.setDistrictName("Шевченковский (Бабушкинский) район");
        sub.setChatId(CHAT_ID);

        this.callbackHandler = new SubToDistrictCallbackHandler(sendMessageService, districtSubService, easyBaseClient);
    }

    @Test
    public void shouldProperlyHandleCallback() {
        // given
        Mockito.when(districtSubService.createSub(CHAT_ID, DISTRICT_ID)).thenReturn(sub);
        EditMessageText expectedReply = createReplyEditMessage(DISTRICT_LIST_MESSAGE);

        // when
        BotApiMethod<?> actualResult = callbackHandler.handleCallback(callbackQuery);

        // then
        Assertions.assertEquals(expectedReply.getText(), ((EditMessageText) actualResult).getText());
        Assertions.assertEquals(expectedReply.getChatId(), ((EditMessageText) actualResult).getChatId());;
    }

    @Test
    public void shouldReturnMessageAboutDuplicateSub() {
        // given
        Mockito.when(districtSubService.getSub(CHAT_ID, DISTRICT_ID)).thenReturn(Optional.of(sub));
        AnswerCallbackQuery expectedReply = createAnswerCallbackQuery(getDuplicateReplyMessage(), true);

        // when
        BotApiMethod<?> actualResult = callbackHandler.handleCallback(callbackQuery);

        // then
        Assertions.assertEquals(expectedReply.getText(), ((AnswerCallbackQuery) actualResult).getText());
    }

    private String getSuccessReplyMessage() {
        return String.format(SUCCESS_SUB_MESSAGE, "Шевченковский (Бабушкинский) район");
    }

    private String getDuplicateReplyMessage() {
        return String.format(DUPLICATE_SUB_MESSAGE, "Шевченковский (Бабушкинский) район");
    }

    private EditMessageText createReplyEditMessage(String text) {
        EditMessageText message = new EditMessageText();
        message.setChatId(CHAT_ID);
        message.setText(text);
        message.enableHtml(true);
        message.setReplyMarkup(new InlineKeyboardMarkup());
        return message;
    }

    private AnswerCallbackQuery createAnswerCallbackQuery(String text, boolean alert) {
        AnswerCallbackQuery answer = new AnswerCallbackQuery();
        answer.setCallbackQueryId(CALLBACK_ID);
        answer.setShowAlert(alert);
        answer.setText(text);
        return answer;
    }
}
