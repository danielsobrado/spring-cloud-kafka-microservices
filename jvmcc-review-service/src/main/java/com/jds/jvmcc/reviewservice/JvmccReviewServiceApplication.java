package com.jds.jvmcc.reviewservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-05
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages = "com.jds.jvmcc.reviewservice.repository")
@OpenAPIDefinition(info = @Info(title = "Reviews API", version = "1.0", description = "Reviews Information"))
public class JvmccReviewServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JvmccReviewServiceApplication.class, args);
	}

	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}