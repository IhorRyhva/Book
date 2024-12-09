package com.firstProject.discavery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscaveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscaveryApplication.class, args);
	}

}
