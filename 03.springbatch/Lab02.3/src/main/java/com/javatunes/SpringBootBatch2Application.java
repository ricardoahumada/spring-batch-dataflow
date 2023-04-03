package com.javatunes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
public class SpringBootBatch2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBatch2Application.class, args);
	}
}
