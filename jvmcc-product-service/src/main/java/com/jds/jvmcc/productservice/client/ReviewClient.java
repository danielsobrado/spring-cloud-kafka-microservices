package com.jds.jvmcc.productservice.client;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jds.jvmcc.productservice.entity.Review;
import com.jds.jvmcc.productservice.exception.HandleFeignException;
import com.jds.jvmcc.productservice.exception.ReviewConsumerExceptionHandler;

import feign.Headers;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-13
 */
public interface ReviewClient {

    @Headers("Content-Type: application/json")
    @HandleFeignException(ReviewConsumerExceptionHandler.class)
    @GetMapping(value = "/review/")
    List<Review> getReviews();

    @Headers("Content-Type: application/json")
    @HandleFeignException(ReviewConsumerExceptionHandler.class)
    @GetMapping(value = "/review/{productId}", produces = "application/json")
    List<Review> getReviewsByProductId(@PathVariable("productId") String productId);

}