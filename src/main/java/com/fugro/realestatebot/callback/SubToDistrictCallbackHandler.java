package com.fugro.realestatebot.callback;

import com.fugro.realestatebot.bot.BotUtils;
import com.fugro.realestatebot.bot.KeyboardFactory;
import com.fugro.realestatebot.client.EasyBaseClient;
import com.fugro.realestatebot.client.dto.DistrictDTO;
import com.fugro.realestatebot.domain.DistrictSub;
import com.fugro.realestatebot.service.DistrictSubService;
import com.fugro.realestatebot.service.SendMessageService;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.List;
import java.util.Optional;

import static com.fugro.realestatebot.command.DistrictListCommand.DISTRICT_LIST_MESSAGE;

public class SubToDistrictCallbackHandler implements CallbackHandler {

    private final SendMessageService sendMessageService;
    private final DistrictSubService districtSubService;
    private final EasyBaseClient easyBaseClient;

    public SubToDistrictCallbackHandler(SendMessageService sendMessageService,
                                        DistrictSubService districtSubService,
                                        EasyBaseClient easyBaseClient
    ) {
        this.sendMessageService = sendMessageService;
        this.districtSubService = districtSubService;
        this.easyBaseClient = easyBaseClient;
    }

    public static final String SUCCESS_SUB_MESSAGE = "Great! Now you will receive updates for %s";
    public static final String DUPLICATE_SUB_MESSAGE = "Yoy! You have already subscribed to updates for %s";

    @Override
    public CallbackType getCallbackType() {
        return CallbackType.SUB_TO_DISTRICT;
    }

    @Override
    public BotApiMethod<?> handleCallback(CallbackQuery callbackQuery) {
        String chatId = BotUtils.getChatId(callbackQuery);

        Integer districtId = Integer.parseInt(callbackQuery.getData().split("=")[1]);

        Optional<DistrictSub> sub = districtSubService.getSub(chatId, districtId);

        if (sub.isPresent()) {
            String replyText = String.format(DUPLICATE_SUB_MESSAGE, sub.get().getDistrictName());
            return sendMessageService.getAnswerCallbackQuery(replyText, true, callbackQuery);
        } else {
            DistrictSub newSub = districtSubService.createSub(chatId, districtId);

            // send pop up
            String replyText = String.format(SUCCESS_SUB_MESSAGE, newSub.getDistrictName());
            sendMessageService.sendAnswerCallbackQuery(replyText, false, callbackQuery);

            // get updated keyboard
            List<DistrictSub> userSubs = districtSubService.getUserSubs(chatId);
            List<DistrictDTO> allDistricts = easyBaseClient.getAllDistricts();
            InlineKeyboardMarkup keyboard = KeyboardFactory.getDistrictListKeyboard(allDistricts, userSubs);

            // update message
            Integer messageId = callbackQuery.getMessage().getMessageId();
            return sendMessageService.getEditMessage(chatId, messageId, DISTRICT_LIST_MESSAGE, keyboard);
        }
    }
}
