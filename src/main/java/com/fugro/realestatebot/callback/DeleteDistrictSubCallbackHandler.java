package com.fugro.realestatebot.callback;

import com.fugro.realestatebot.bot.BotUtils;
import com.fugro.realestatebot.bot.KeyboardFactory;
import com.fugro.realestatebot.domain.DistrictSub;
import com.fugro.realestatebot.service.DistrictSubService;
import com.fugro.realestatebot.service.SendMessageService;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.List;

import static com.fugro.realestatebot.command.MySubsCommand.MY_SUBS_MESSAGE;
import static com.fugro.realestatebot.command.MySubsCommand.NO_SUBS_MESSAGE;

public class DeleteDistrictSubCallbackHandler implements CallbackHandler {

    private final SendMessageService sendMessageService;
    private final DistrictSubService districtSubService;

    public DeleteDistrictSubCallbackHandler(SendMessageService sendMessageService, DistrictSubService districtSubService) {
        this.sendMessageService = sendMessageService;
        this.districtSubService = districtSubService;
    }

    public static final String SUCCESS_DELETION_MESSAGE = "Subscription removed";
    public static final String ERROR_DELETION_MESSAGE = "Ooops! Subscription was not removed";

    @Override
    public CallbackType getCallbackType() {
        return CallbackType.DELETE_DISTRICT_SUB;
    }

    @Override
    public BotApiMethod<?> handleCallback(CallbackQuery callbackQuery) {
        String chatId = BotUtils.getChatId(callbackQuery);

        Integer districtId = Integer.parseInt(callbackQuery.getData().split("=")[1]);

        boolean isRemoved = districtSubService.deleteSub(chatId, districtId);

        if (isRemoved) {
            Integer messageId = callbackQuery.getMessage().getMessageId();
            List<DistrictSub> subs = districtSubService.getUserSubs(chatId);

            sendMessageService.sendAnswerCallbackQuery(SUCCESS_DELETION_MESSAGE, false, callbackQuery);

            if (subs.isEmpty()) {
                return sendMessageService.getEditMessage(chatId, messageId, NO_SUBS_MESSAGE);
            }

            InlineKeyboardMarkup keyboard = KeyboardFactory.getActiveSubsKeyboard(subs);
            return sendMessageService.getEditMessage(chatId, messageId, MY_SUBS_MESSAGE, keyboard);
        }

        return sendMessageService.getMessage(chatId, ERROR_DELETION_MESSAGE);
    }
}
