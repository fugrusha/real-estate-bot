package com.fugro.realestatebot.service.impl;

import com.fugro.realestatebot.client.EasyBaseClient;
import com.fugro.realestatebot.client.dto.DistrictDTO;
import com.fugro.realestatebot.domain.DistrictSub;
import com.fugro.realestatebot.domain.TelegramUser;
import com.fugro.realestatebot.repository.DistrictSubRepository;
import com.fugro.realestatebot.service.DistrictSubService;
import com.fugro.realestatebot.service.TelegramUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DistrictSubServiceImpl implements DistrictSubService {

    private final DistrictSubRepository districtSubRepository;
    private final TelegramUserService userService;
    private final EasyBaseClient easyBaseClient;

    @Autowired
    public DistrictSubServiceImpl(DistrictSubRepository districtSubRepository,
                                  TelegramUserService userService,
                                  EasyBaseClient easyBaseClient
    ) {
        this.districtSubRepository = districtSubRepository;
        this.userService = userService;
        this.easyBaseClient = easyBaseClient;
    }

    @Override
    public List<DistrictSub> getUserSubs(String chatId) {
        return districtSubRepository.findByChatId(chatId);
    }

    @Override
    public List<DistrictSub> getAllSubs() {
        return districtSubRepository.findAll();
    }

    @Override
    public DistrictSub updateSentIdsSet(String chatId, Integer districtId, Integer newExternalId) {
        Optional<DistrictSub> subOptional = districtSubRepository.findByChatIdAndDistrictId(chatId, districtId);
        if (subOptional.isPresent()) {
            DistrictSub sub = subOptional.get();
            sub.getSentAdIds().add(newExternalId);
            return districtSubRepository.save(sub);
        }
        throw new RuntimeException("DistrictSub not found for chatId=" + chatId + " and districtId=" + districtId);
    }

    @Override
    public Optional<DistrictSub> getSub(String chatId, Integer districtId) {
        return districtSubRepository.findByChatIdAndDistrictId(chatId, districtId);
    }

    @Override
    public DistrictSub createSub(String chatId, Integer districtId) {
        TelegramUser user = userService.findByChatId(chatId);

        List<DistrictDTO> districts = easyBaseClient.getAllDistricts();

        Optional<DistrictDTO> districtDTO = districts.stream()
                .filter(dto -> dto.getId().equals(districtId))
                .findFirst();

        DistrictSub sub = new DistrictSub();
        sub.setChatId(user.getChatId());
        sub.setDistrictId(districtId);
        sub.setDistrictName(districtDTO.isPresent() ? districtDTO.get().getName() : "Unknown");

        return districtSubRepository.save(sub);
    }

    @Override
    public boolean deleteSub(String chatId, Integer districtId) {
        Optional<DistrictSub> optionalSub = districtSubRepository.findByChatIdAndDistrictId(chatId, districtId);
        if (optionalSub.isPresent()) {
            DistrictSub sub = optionalSub.get();
            districtSubRepository.delete(sub);
            return true;
        }
        return false;
    }

    @Override
    public void deleteAllSubs(String chatId) {
        districtSubRepository.deleteByChatId(chatId);
    }
}
