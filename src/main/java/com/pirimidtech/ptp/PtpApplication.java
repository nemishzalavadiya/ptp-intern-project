package com.pirimidtech.ptp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.TimeZone;

@EntityScan
@SpringBootApplication
@EnableScheduling
public class PtpApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(PtpApplication.class, args);
    }

}
