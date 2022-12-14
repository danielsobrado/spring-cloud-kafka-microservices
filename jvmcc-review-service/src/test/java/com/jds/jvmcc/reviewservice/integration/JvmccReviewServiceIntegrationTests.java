package com.jds.jvmcc.reviewservice.integration;

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
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.jds.jvmcc.reviewservice.JvmccReviewServiceApplication;
import com.jds.jvmcc.reviewservice.entity.Review;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-10
 * 
 * This test case requires Docker container to be running.
 */
@ActiveProfiles("test")
@TestPropertySource(locations="classpath:application-test.yml")
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

    @Test
    @Sql({ "/initSQL.sql" })
    public void testGetReviews() {

        ResponseEntity<Review> response = testRestTemplate.getForEntity( "/reviews/{productId}",Review.class,"M20324");
        Review review =  response.getBody();

		assertEquals("M20324",review.getProductId());

    }

}
