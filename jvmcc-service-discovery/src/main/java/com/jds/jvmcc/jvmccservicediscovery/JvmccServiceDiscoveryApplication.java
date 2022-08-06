package com.jds.jvmcc.jvmccservicediscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-06
 */
@SpringBootApplication
@EnableEurekaServer
public class JvmccServiceDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(JvmccServiceDiscoveryApplication.class, args);
	}

}
