package com.fugro.realestatebot.service.impl;

import com.fugro.realestatebot.domain.TelegramUser;
import com.fugro.realestatebot.repository.TelegramUserRepository;
import com.fugro.realestatebot.service.TelegramUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelegramUserServiceImpl implements TelegramUserService {

    private TelegramUserRepository userRepository;

    @Autowired
    public TelegramUserServiceImpl(TelegramUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void save(TelegramUser telegramUser) {
        userRepository.save(telegramUser);
    }

    @Override
    public List<TelegramUser> getAllActiveUsers() {
        return userRepository.findAllByIsActiveTrue();
    }

    @Override
    public TelegramUser findByChatId(String chatId) {
        return userRepository.findByChatId(chatId);
    }
}
