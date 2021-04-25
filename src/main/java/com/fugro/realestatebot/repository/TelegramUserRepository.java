package com.fugro.realestatebot.repository;

import com.fugro.realestatebot.domain.TelegramUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelegramUserRepository extends MongoRepository<TelegramUser, String> {

    TelegramUser findByChatId(String chatId);

    List<TelegramUser> findAllByIsActiveTrue();
}
