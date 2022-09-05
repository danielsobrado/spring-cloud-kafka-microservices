package com.jds.jvmcc.reviewservice.integration;

import static com.jds.jvmcc.reviewservice.integration.ReviewMocks.setupMockReviewsResponse;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.jds.jvmcc.reviewservice.service.impl.ReviewServiceImpl;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-09-05
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class,
    ManagementWebSecurityAutoConfiguration.class })
@TestPropertySource(locations="classpath:application-test.yml")
@SpringBootTest(classes = {com.jds.jvmcc.reviewservice.JvmccReviewServiceApplication.class, 
	com.jds.jvmcc.reviewservice.service.impl.ReviewServiceImpl.class,
	com.jds.jvmcc.reviewservice.repository.ReviewRepository.class, })
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { WireMockConfig.class })
public class ReviewServiceIntegrationTest {
    
    @MockBean
    private JwtDecoder jwtDecoder;

    @Autowired
    private WireMockServer mockReviewsService;

    @Autowired
    private ReviewServiceImpl reviewService;

    @BeforeEach
    public void setup() throws IOException {
        setupMockReviewsResponse(mockReviewsService);
    }

    @Test
    public void testGetReviews() {

        mockReviewsService.verify(2, WireMock.getRequestedFor(WireMock.urlEqualTo("/review/B41234")));

    }
}
