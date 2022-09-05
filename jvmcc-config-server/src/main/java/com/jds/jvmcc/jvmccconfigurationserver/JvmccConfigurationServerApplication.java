package com.jds.jvmcc.jvmccconfigurationserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-06
 */
@EnableConfigServer
@SpringBootApplication
public class JvmccConfigurationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JvmccConfigurationServerApplication.class, args);
	}

}
