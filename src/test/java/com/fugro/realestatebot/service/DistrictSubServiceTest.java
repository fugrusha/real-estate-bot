package com.fugro.realestatebot.service;

import com.fugro.realestatebot.client.EasyBaseClient;
import com.fugro.realestatebot.domain.DistrictSub;
import com.fugro.realestatebot.domain.TelegramUser;
import com.fugro.realestatebot.repository.DistrictSubRepository;
import com.fugro.realestatebot.service.impl.DistrictSubServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class DistrictSubServiceTest {

    private DistrictSubService districtSubService;
    private DistrictSubRepository districtSubRepository;

    private static final String CHAT_ID = "22";
    private static final Integer DISTRICT_ID = 77;
    private static final String DISTRICT_NAME = "Solone";

    @BeforeEach
    public void init() {
        TelegramUserService userService = Mockito.mock(TelegramUserService.class);
        EasyBaseClient easyBaseClient = Mockito.mock(EasyBaseClient.class);
        districtSubRepository = Mockito.mock(DistrictSubRepository.class);
        districtSubService = new DistrictSubServiceImpl(districtSubRepository, userService, easyBaseClient);

        TelegramUser telegramUser = new TelegramUser();
        telegramUser.setActive(true);
        telegramUser.setChatId(CHAT_ID);

        Mockito.when(userService.findByChatId(CHAT_ID)).thenReturn(telegramUser);
    }

    @Test
    public void shouldReturnSub() {

    }

    @Test
    public void shouldReturnAllUsersSubs() {

    }

    @Test
    public void shouldSaveSubscription() {
        // given
        DistrictSub expectedSub = new DistrictSub();
        expectedSub.setChatId(CHAT_ID);
        expectedSub.setDistrictId(DISTRICT_ID);
        expectedSub.setDistrictName("Unknown");

        // when
        districtSubService.createSub(CHAT_ID, DISTRICT_ID);

        // then
        Mockito.verify(districtSubRepository).save(expectedSub);
    }

    @Test
    public void shouldDeleteSubscription() {
        // given
        DistrictSub expectedSub = new DistrictSub();
        expectedSub.setChatId(CHAT_ID);
        expectedSub.setDistrictId(DISTRICT_ID);
        expectedSub.setDistrictName(DISTRICT_NAME);

        Mockito.when(districtSubRepository.findByChatIdAndDistrictId(CHAT_ID, DISTRICT_ID))
                .thenReturn(Optional.of(expectedSub));

        // when
        districtSubService.deleteSub(CHAT_ID, DISTRICT_ID);

        // then
        Mockito.verify(districtSubRepository).findByChatIdAndDistrictId(CHAT_ID, DISTRICT_ID);
        Mockito.verify(districtSubRepository).delete(expectedSub);
    }
}
