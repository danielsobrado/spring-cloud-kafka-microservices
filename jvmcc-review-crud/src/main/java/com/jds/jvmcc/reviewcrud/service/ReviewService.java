package com.jds.jvmcc.reviewcrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jds.jvmcc.reviewcrud.entity.Review;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-05
 */
@Service
public class ReviewService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * @return
     */
    public Review getReview(String productId) {
        return restTemplate.getForObject("http://review-crud-service/reviews/" + productId, Review.class);
    }

    /**
     * @return
     */
    public List<Review> getReviewList() {
        return (List<Review>)restTemplate.getForObject("http://review-crud-service/reviews", List.class);
    }

}
