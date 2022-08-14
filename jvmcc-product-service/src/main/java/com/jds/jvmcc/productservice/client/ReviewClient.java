package com.jds.jvmcc.productservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jds.jvmcc.productservice.config.LocalClientConfiguration;
import com.jds.jvmcc.productservice.entity.Review;
import com.jds.jvmcc.productservice.fallback.ReviewFallback;

import feign.Headers;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-13
 */
@FeignClient(name = "review-client", 
    url = "http://jvmcc:jvmcc@localhost:8091/", 
    configuration = LocalClientConfiguration.class, 
    fallback = ReviewFallback.class)
public interface ReviewClient {

    @Headers("Content-Type: application/json")
    @GetMapping(value = "/review/")
    List<Review> getReviews();

    @Headers("Content-Type: application/json")
    @GetMapping(value = "/review/{productId}", produces = "application/json")
    List<Review> getReviewsByProductId(@PathVariable("productId") String productId);

}