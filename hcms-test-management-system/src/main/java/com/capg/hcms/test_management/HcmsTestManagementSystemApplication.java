package com.capg.hcms.test_management;

import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HcmsTestManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HcmsTestManagementSystemApplication.class, args);
	}
@Bean
public Random getRandomValue()
{
return new Random();	
}
}
