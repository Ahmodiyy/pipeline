package com.example.pipeline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PipelineApplication {
	private static final String CONSTANT = "constant";
	public static void main(String[] args) {
		SpringApplication.run(PipelineApplication.class, args);
	}

}
