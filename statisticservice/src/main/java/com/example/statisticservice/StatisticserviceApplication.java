package com.example.statisticservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class StatisticserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StatisticserviceApplication.class, args);
	}
}
