package com.pirimidtech.ptp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
@SpringBootApplication
public class PtpApplication {

	public static void main(String[] args) {
		SpringApplication.run(PtpApplication.class, args);
	}

}
