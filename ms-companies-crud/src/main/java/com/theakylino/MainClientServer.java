package com.theakylino;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MainClientServer {

	public static void main(String[] args) {
		SpringApplication.run(MainClientServer.class, args);
	}

}
