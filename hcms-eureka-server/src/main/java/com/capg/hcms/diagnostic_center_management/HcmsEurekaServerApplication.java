package com.capg.hcms.diagnostic_center_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class HcmsEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HcmsEurekaServerApplication.class, args);
	}

}
