package com.jds.jvmcc.reviewservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-05
 * 
 * Blocking implementation for the review service.
 */
@SpringBootApplication(exclude = {
    SecurityAutoConfiguration.class,
    ManagementWebSecurityAutoConfiguration.class})
@EnableDiscoveryClient
@EnableCaching
@EnableJpaRepositories(basePackages = "com.jds.jvmcc.reviewservice.repository")
@OpenAPIDefinition(info = @Info(title = "Reviews API", version = "1.0", description = "Reviews Information"))
public class JvmccReviewServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JvmccReviewServiceApplication.class, args);
	}

}
