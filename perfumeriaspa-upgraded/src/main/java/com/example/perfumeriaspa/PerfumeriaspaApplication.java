package com.example.perfumeriaspa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PerfumeriaspaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PerfumeriaspaApplication.class, args);
	}
}
