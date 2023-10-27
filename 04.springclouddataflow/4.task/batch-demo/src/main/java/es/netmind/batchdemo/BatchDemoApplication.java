package com.banana.batchdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;

@EnableTask
@SpringBootApplication
public class BatchDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(BatchDemoApplication.class, args);
	}

}
