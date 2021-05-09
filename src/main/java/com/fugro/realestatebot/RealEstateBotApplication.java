package com.fugro.realestatebot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RealEstateBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealEstateBotApplication.class, args);
    }

}
