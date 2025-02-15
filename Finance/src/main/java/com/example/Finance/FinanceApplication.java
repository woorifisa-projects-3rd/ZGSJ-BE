package com.example.Finance;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@Slf4j
public class FinanceApplication {

	public static void main(String[] args) {
		log.info("finance 11-28ver");
		SpringApplication.run(FinanceApplication.class, args);
	}

}
