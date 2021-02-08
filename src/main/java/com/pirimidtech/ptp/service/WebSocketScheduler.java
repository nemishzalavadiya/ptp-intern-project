package com.pirimidtech.ptp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import javax.xml.crypto.Data;

@EnableScheduling
@Configuration
public class WebSocketScheduler {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    private DataGenerator dataGenerator;

    public WebSocketScheduler(){
        dataGenerator= new DataGenerator();
        dataGenerator.setDistinctCompanyId();
    }

    @Scheduled(fixedDelay = 1000)
    public void trigger() {
        dataGenerator.setData();
        DataGenerator.dataGeneratorList.forEach((companyData)->{
            this.simpMessagingTemplate.convertAndSend("/topic/"+companyData.company_id,companyData);
        });
    }
}
