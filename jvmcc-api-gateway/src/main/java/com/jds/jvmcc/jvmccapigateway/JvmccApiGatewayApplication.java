package com.jds.jvmcc.jvmccapigateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class JvmccApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(JvmccApiGatewayApplication.class, args);
	}

	@Autowired
	private DiscoveryClient discoveryClient;

}
