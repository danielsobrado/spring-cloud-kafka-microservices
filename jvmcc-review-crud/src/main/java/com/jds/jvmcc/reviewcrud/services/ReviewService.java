package com.jds.jvmcc.reviewcrud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jds.jvmcc.reviewcrud.entities.Review;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-05
 */
@Service
public class ReviewService {

    @Autowired
    private RestTemplate restTemplate;

    public Review getReview(String productId) {
        return restTemplate.getForObject("http://review-crud-service/reviews/" + productId, Review.class);
    }

}
