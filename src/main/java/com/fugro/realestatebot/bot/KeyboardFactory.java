package com.fugro.realestatebot.bot;

import com.fugro.realestatebot.callback.CallbackType;
import com.fugro.realestatebot.client.dto.DistrictDTO;
import com.fugro.realestatebot.command.CommandName;
import com.fugro.realestatebot.domain.DistrictSub;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class KeyboardFactory {

    private static final String DELIMITER = "=";

    public static ReplyKeyboardMarkup getMainMenuKeyboard() {

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton(CommandName.DISTRICT_LIST.getCommandName()));
        row1.add(new KeyboardButton(CommandName.MY_SUBS.getCommandName()));

        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton(CommandName.STATS.getCommandName()));
        row2.add(new KeyboardButton(CommandName.HELP.getCommandName()));

        KeyboardRow row3 = new KeyboardRow();
        row3.add(new KeyboardButton(CommandName.START.getCommandName()));
        row3.add(new KeyboardButton(CommandName.STOP.getCommandName()));

        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);

        replyKeyboardMarkup.setKeyboard(keyboard);

        return replyKeyboardMarkup;
    }

    public static ReplyKeyboard getDistrictListKeyboard(List<DistrictDTO> districts) {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (DistrictDTO dto : districts) {
            Integer id = dto.getId();
            String name = dto.getName();

            List<InlineKeyboardButton> row = new ArrayList<>();

            InlineKeyboardButton button = new InlineKeyboardButton(name);
            button.setCallbackData(CallbackType.SUB_TO_DISTRICT + DELIMITER + id);
            row.add(button);

            keyboard.add(row);
        }

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }

    public static ReplyKeyboard getActiveSubsKeyboard(List<DistrictSub> activeSubs) {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (DistrictSub sub : activeSubs) {
            Integer id = sub.getDistrictId();
            String name = sub.getDistrictName();

            List<InlineKeyboardButton> row = new ArrayList<>();

            InlineKeyboardButton button1 = new InlineKeyboardButton(name);
            button1.setCallbackData(CallbackType.IGNORE.toString());
            row.add(button1);

            InlineKeyboardButton button2 = new InlineKeyboardButton("DELETE");
            button2.setCallbackData(CallbackType.DELETE_DISTRICT_SUB + DELIMITER + id);
            row.add(button2);

            keyboard.add(row);
        }

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }
}
