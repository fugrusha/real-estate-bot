package com.fugro.realestatebot.job;

import com.fugro.realestatebot.service.FindNewAdsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Slf4j
@Component
public class FindNewAdsJob {

    private final FindNewAdsService findNewAdsService;

    @Autowired
    public FindNewAdsJob(FindNewAdsService findNewAdsService) {
        this.findNewAdsService = findNewAdsService;
    }

    @Scheduled(fixedRateString = "${bot.findnewadsjob.fixed.rate}")
    public void findNewAdsAndNotifySubscribers() {
        LocalDateTime start = LocalDateTime.now();
        log.info("FindNewAdsJob started at {}", start);

        findNewAdsService.findNewAdsAndNotifyUsers();

        LocalDateTime finish = LocalDateTime.now();
        log.info("FindNewAdsJob finished. Took seconds {}",
                finish.toEpochSecond(ZoneOffset.UTC) - start.toEpochSecond(ZoneOffset.UTC));
    }
}
