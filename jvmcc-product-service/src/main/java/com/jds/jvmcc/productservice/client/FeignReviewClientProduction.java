package com.jds.jvmcc.productservice.client;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;

import com.jds.jvmcc.productservice.config.LoadBalancerConfiguration;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-29
 */
@Profile("production")
@FeignClient(value = "jvmcc-review-service")
@LoadBalancerClient(name = "jvmcc-review-service", configuration=LoadBalancerConfiguration.class)
public interface FeignReviewClientProduction extends ReviewClient {
}