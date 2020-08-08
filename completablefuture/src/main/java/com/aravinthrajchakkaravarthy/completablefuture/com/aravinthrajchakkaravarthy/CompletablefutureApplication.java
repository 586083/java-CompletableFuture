package com.aravinthrajchakkaravarthy.completablefuture.com.aravinthrajchakkaravarthy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableAsync
public class CompletablefutureApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompletablefutureApplication.class, args);
	}

}
