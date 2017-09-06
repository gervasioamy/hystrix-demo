package com.globant.hystrixdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDemoDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixDemoDashboardApplication.class, args);
	}
}
