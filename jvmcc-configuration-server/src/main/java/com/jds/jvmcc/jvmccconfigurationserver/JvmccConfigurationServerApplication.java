package com.jds.jvmcc.jvmccconfigurationserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-06
 */
@EnableConfigServer
@SpringBootApplication
public class JvmccConfigurationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(JvmccConfigurationServerApplication.class, args);
	}

}
