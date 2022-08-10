package com.jds.jvmcc.reviewservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.jds.jvmcc.reviewservice.JvmccReviewServiceApplication;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-10
 */
@SpringBootTest(classes = JvmccReviewServiceApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@Testcontainers
@DirtiesContext
class JvmccReviewServiceIntegrationTests {

	@Container
	public static MySQLContainer<?> mySqlDB = new MySQLContainer<>
			("mysql:8.0.30")
			.withDatabaseName("jvmcc-db")
			.withUsername("admin")
			.withPassword("admin");

	@DynamicPropertySource
	public static void properties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url",mySqlDB::getJdbcUrl);
		registry.add("spring.datasource.username", mySqlDB::getUsername);
		registry.add("spring.datasource.password", mySqlDB::getPassword);

	}

	@Test
	void contextLoads() {
	}

}
