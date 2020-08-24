package com.capg.hcms.usermanagementsystem;

import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
public class HcmsUserManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HcmsUserManagementSystemApplication.class, args);
	}

@Bean
@LoadBalanced
public RestTemplate getRestTemplate()
{
return new RestTemplate();	
}
@Bean
public Random getRandom()
{
return new Random();
}

}
