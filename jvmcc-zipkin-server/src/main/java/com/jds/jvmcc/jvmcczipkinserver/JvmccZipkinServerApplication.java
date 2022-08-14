package com.jds.jvmcc.jvmcczipkinserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import zipkin.server.EnableZipkinServer;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-14
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZipkinServer
public class JvmcZipkinServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JvmcZipkinServerApplication.class, args);
	}

}
