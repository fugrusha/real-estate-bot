package com.fugro.realestatebot.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "telegram_user")
public class TelegramUser {

    @Id
    private String id;

    @Indexed(unique = true)
    private String chatId;

    private boolean isActive;
}
