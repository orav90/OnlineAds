package com.ads;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class OnlineSponsoredAdsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineSponsoredAdsApplication.class, args);
	}

}
