package com.pirimidtech.ptp.service;

import com.pirimidtech.ptp.service.datagenerator.DataGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
public class WebSocketScheduler {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private DataGenerator dataGenerator;

    @Scheduled(fixedDelay = 3000)
    public void trigger() {
        dataGenerator.setData();
        dataGenerator.getGeneratedStockList().forEach((companyData) -> {
            this.simpMessagingTemplate.convertAndSend("/topic/" + companyData.getCompanyId(), companyData);
        });
    }
}
