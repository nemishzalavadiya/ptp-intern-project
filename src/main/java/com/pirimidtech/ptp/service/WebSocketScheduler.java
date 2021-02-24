package com.pirimidtech.ptp.service;

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

    @Scheduled(fixedDelay = 1000)
    public void trigger() {
        dataGenerator.setDistinctCompany();
        dataGenerator.setData();
        DataGenerator.dataGeneratorList.forEach((companyData) -> {
            this.simpMessagingTemplate.convertAndSend("/topic/" + companyData.company_id, companyData);
        });
        DataGenerator.companyNameList.clear();
        DataGenerator.companyIdList.clear();
    }
}
