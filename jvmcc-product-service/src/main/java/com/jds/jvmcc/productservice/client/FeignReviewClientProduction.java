package com.jds.jvmcc.productservice.client;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;

import com.jds.jvmcc.productservice.config.LocalClientConfiguration;
import com.jds.jvmcc.productservice.fallback.ReviewFallback;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-29
 */
@Profile("production")
@FeignClient(value = "jvmcc-review-service", 
    url = "http://localhost:8093",
    configuration = LocalClientConfiguration.class, 
    fallback = ReviewFallback.class)
public interface FeignReviewClientProduction extends ReviewClient {
}