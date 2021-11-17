package com.pawan.springboo.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class SpringbootBatchReadFromDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBatchReadFromDbApplication.class, args);
	}
}
