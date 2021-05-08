package com.fugro.realestatebot.callback;

import com.fugro.realestatebot.bot.RealEstateBot;
import com.fugro.realestatebot.domain.DistrictSub;
import com.fugro.realestatebot.service.DistrictSubService;
import com.fugro.realestatebot.service.SendMessageService;
import com.fugro.realestatebot.service.impl.SendMessageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Optional;

import static com.fugro.realestatebot.callback.SubToDistrictCallbackHandler.DUPLICATE_SUB_MESSAGE;
import static com.fugro.realestatebot.callback.SubToDistrictCallbackHandler.SUCCESS_SUB_MESSAGE;

public class SubToDistrictCallbackHandlerTest {

    private final RealEstateBot realEstateBot = Mockito.mock(RealEstateBot.class);
    private final DistrictSubService districtSubService = Mockito.mock(DistrictSubService.class);
    private final SendMessageService sendMessageService = new SendMessageServiceImpl(realEstateBot);

    private final int DISTRICT_ID = 2;
    private final String CHAT_ID = "1234";

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

        sub = new DistrictSub();
        sub.setDistrictId(DISTRICT_ID);
        sub.setDistrictName("Шевченковский (Бабушкинский) район");
        sub.setChatId(CHAT_ID);

        this.callbackHandler = new SubToDistrictCallbackHandler(sendMessageService, districtSubService);
    }

    @Test
    public void shouldProperlyHandleCallback() throws Exception {
        // given
        Mockito.when(districtSubService.createSub(CHAT_ID, DISTRICT_ID)).thenReturn(sub);
        SendMessage expectedReply = createReplySendMessage(getSuccessReplyMessage());

        // when
        callbackHandler.handleCallback(callbackQuery);

        // then
        Mockito.verify(realEstateBot).execute(expectedReply);
    }

    @Test
    public void shouldReturnMessageAboutDuplicateSub() throws Exception {
        // given
        Mockito.when(districtSubService.getSub(CHAT_ID, DISTRICT_ID)).thenReturn(Optional.of(sub));
        SendMessage expectedReply = createReplySendMessage(getDuplicateReplyMessage());

        // when
        callbackHandler.handleCallback(callbackQuery);

        // then
        Mockito.verify(realEstateBot).execute(expectedReply);
    }

    private String getSuccessReplyMessage() {
        return String.format(SUCCESS_SUB_MESSAGE, "Шевченковский (Бабушкинский) район");
    }

    private String getDuplicateReplyMessage() {
        return String.format(DUPLICATE_SUB_MESSAGE, "Шевченковский (Бабушкинский) район");
    }

    protected SendMessage createReplySendMessage(String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(CHAT_ID);
        sendMessage.setText(message);
        sendMessage.enableHtml(true);
        return sendMessage;
    }
}
