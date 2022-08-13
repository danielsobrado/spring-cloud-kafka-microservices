package com.jds.jvmcc.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-05
 * 
 * Blocking implementation for the product service.
 */
// @EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class JvmccProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JvmccProductServiceApplication.class, args);
	}

}
