package com.jds.jvmcc.jvmccapigateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-05
 */
@SpringBootApplication
@EnableDiscoveryClient
public class JvmccApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(JvmccApiGatewayApplication.class, args);
	}

	@Autowired
	private DiscoveryClient discoveryClient;

}
