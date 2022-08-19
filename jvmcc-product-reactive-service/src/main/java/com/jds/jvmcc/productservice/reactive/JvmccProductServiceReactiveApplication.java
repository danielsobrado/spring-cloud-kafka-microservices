package com.jds.jvmcc.productservice.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-13
 * 
 * Non-blocking reactive implementation for the product service.
 */
// @EnableDiscoveryClient
@SpringBootApplication(exclude = {
    SecurityAutoConfiguration.class,
    ManagementWebSecurityAutoConfiguration.class})
public class JvmccProductServiceReactiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(JvmccProductServiceReactiveApplication.class, args);
	}

}
