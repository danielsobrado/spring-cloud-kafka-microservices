package com.jds.jvmcc.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-05
 * 
 * Blocking implementation for the product service.
 * Product Consumption from Adidas URL and Reviews Information (Aggregated statistics).
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication(exclude = {
    DataSourceAutoConfiguration.class, 
    DataSourceTransactionManagerAutoConfiguration.class, 
    HibernateJpaAutoConfiguration.class,
    ManagementWebSecurityAutoConfiguration.class})
@OpenAPIDefinition(info = @Info(title = "Products API", version = "1.0", description = "Product Consumption and Reviews Information"))
public class JvmccProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(JvmccProductServiceApplication.class, args);
	}

}
