package com.example.Attendance;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@Slf4j
public class AttendanceApplication {

	public static void main(String[] args) {
		log.info("attedance 실행 완료");
		SpringApplication.run(AttendanceApplication.class, args);
	}

}
