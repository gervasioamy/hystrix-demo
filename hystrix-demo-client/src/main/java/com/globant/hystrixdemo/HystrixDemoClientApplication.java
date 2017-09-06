package com.globant.hystrixdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCircuitBreaker
public class HystrixDemoClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixDemoClientApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(){
	    return new RestTemplate();
	};
}
