package com.example.main_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
public class MainServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainServiceApplication.class, args);
	}

}
