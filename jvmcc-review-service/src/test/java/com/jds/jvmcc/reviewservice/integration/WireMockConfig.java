package com.jds.jvmcc.reviewservice.integration;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;

import com.github.tomakehurst.wiremock.WireMockServer;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-09-05
 */
@ActiveProfiles("test")
@TestConfiguration
public class WireMockConfig {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public WireMockServer mockReviewsService() {
        return new WireMockServer(8191);
    }
}
