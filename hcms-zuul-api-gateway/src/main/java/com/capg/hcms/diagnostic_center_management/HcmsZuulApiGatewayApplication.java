package com.capg.hcms.diagnostic_center_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.CrossOrigin;

@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class HcmsZuulApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(HcmsZuulApiGatewayApplication.class, args);
	}

}
