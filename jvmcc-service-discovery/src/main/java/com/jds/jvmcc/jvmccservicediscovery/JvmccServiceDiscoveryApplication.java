package com.jds.jvmcc.jvmccservicediscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class JvmccServiceDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(JvmccServiceDiscoveryApplication.class, args);
	}

}
