package com.fugro.realestatebot.service.impl;

import com.fugro.realestatebot.client.EasyBaseClient;
import com.fugro.realestatebot.client.dto.AdvertImportDTO;
import com.fugro.realestatebot.client.dto.PageResultDTO;
import com.fugro.realestatebot.client.dto.QueryArgs;
import com.fugro.realestatebot.domain.DistrictSub;
import com.fugro.realestatebot.service.DistrictSubService;
import com.fugro.realestatebot.service.FindNewAdsService;
import com.fugro.realestatebot.service.SendMessageService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class FindNewAdsServiceImpl implements FindNewAdsService {

    private final EasyBaseClient easyBaseClient;
    private final DistrictSubService districtSubService;
    private final SendMessageService sendMessageService;

    @Autowired
    public FindNewAdsServiceImpl(EasyBaseClient easyBaseClient,
                                 DistrictSubService districtSubService,
                                 SendMessageService sendMessageService
    ) {
        this.easyBaseClient = easyBaseClient;
        this.districtSubService = districtSubService;
        this.sendMessageService = sendMessageService;
    }

    @Override
    public void findNewAdsAndNotifyUsers() {
        QueryArgs args = QueryArgs.builder()
                .page(1)
                .type("apts")
                .build();

        PageResultDTO pageResult = easyBaseClient.getPageResults(args);
        List<AdvertImportDTO> ads = pageResult.getResults();

        districtSubService.getAllSubs().forEach(sub -> {
            for (AdvertImportDTO ad : ads) {
                if (sub.getDistrictId().equals(ad.getDistrict().getId())) {
                    Integer externalId = ad.getExternalId();

                    if (!sub.getSentAdIds().contains(externalId)) {
                        notifySubscriber(ad, sub);
                        districtSubService.updateSentIdsSet(sub.getChatId(), sub.getDistrictId(), externalId);
                    }
                }
            }
        });
    }

    @SneakyThrows
    private void notifySubscriber(AdvertImportDTO ad, DistrictSub sub) {
        String pattern = "New ad at your district subscription on %s\nCity: %s\nMicro district: %s\nStreet: %s\n" +
                "Total rooms: %d\nFloor: %d\n\nPrice: %s %s";

        String microDistrictName = ad.getMicroDistrict() == null ? "" : ad.getMicroDistrict().getName();
        String streetName = ad.getStreet() == null ? "" : ad.getStreet().getName();

        String message = String.format(pattern, sub.getDistrictName(), ad.getCity(), microDistrictName, streetName,
                ad.getRoomsCount(), ad.getFloorNumber(), ad.getPrice(), ad.getCurrency());

        sendMessageService.sendMessage(sub.getChatId(), message);

        TimeUnit.MILLISECONDS.sleep(200);
    }
}
