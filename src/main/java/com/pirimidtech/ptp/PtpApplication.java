package com.pirimidtech.ptp;

import com.pirimidtech.ptp.entity.StockOrder;
import com.pirimidtech.ptp.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
@SpringBootApplication
public class PtpApplication {

	public static void main(String[] args) {
		SpringApplication.run(PtpApplication.class, args);
	}

}
