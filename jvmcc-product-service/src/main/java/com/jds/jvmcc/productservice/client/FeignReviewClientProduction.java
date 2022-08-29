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
@FeignClient(value = "jvmcc-review", 
    url = "https://127.0.0.1:8080",
    configuration = LocalClientConfiguration.class, 
    fallback = ReviewFallback.class)
@RibbonClient(name = "jvmcc-review")
public interface FeignReviewClientProduction extends ReviewClient {
}