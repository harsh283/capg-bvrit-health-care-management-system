package com.capg.hcms.diagnostic_center_management;

import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class HcmsDiagnosticCenterManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HcmsDiagnosticCenterManagementSystemApplication.class, args);
	}
@Bean
public Random generateRandom()
{
return new Random();	
}
}
