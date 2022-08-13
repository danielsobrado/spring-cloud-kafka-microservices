package com.jds.jvmcc.reviewservice.reactive.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.jds.jvmcc.reviewservice.reactive.JvmccReviewServiceApplication;
import com.jds.jvmcc.reviewservice.reactive.entity.Review;
import com.jds.jvmcc.reviewservice.reactive.service.ReviewService;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-13
 * 
 * This test case requires Docker container to be running.
 */
@ActiveProfiles("test")
@Testcontainers
@DirtiesContext
@EnableAutoConfiguration
@SpringBootTest(classes = JvmccReviewServiceApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
class JvmccReviewServiceIntegrationTests {

	private static final String MYSQL_VERSION = "mysql:8.0.30";

	@Container
	public static MySQLContainer<?> mySqlDB = new MySQLContainer<>(MYSQL_VERSION);

	@DynamicPropertySource
	public static void properties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url",mySqlDB::getJdbcUrl);
		registry.add("spring.datasource.username", mySqlDB::getUsername);
		registry.add("spring.datasource.password", mySqlDB::getPassword);

	}

	@Autowired
	protected TestRestTemplate testRestTemplate ;

	@Autowired
	private ReviewService reviewService;

    @Test
    @Sql({ "/initSQL.sql" })
    public void testGetDepartmentById() {

        ResponseEntity<Review> response = testRestTemplate.getForEntity( "/reviews/{productId}",Review.class,"M20324");
        Review review =  response.getBody();

        // assertEquals((short)3,review.getReviewScore());
		assertEquals("M20324",review.getProductId());
        // assertEquals("Test Comment", review.getComment());

    }

}
