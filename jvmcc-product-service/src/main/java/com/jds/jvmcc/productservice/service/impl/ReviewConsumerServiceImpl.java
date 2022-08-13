package com.jds.jvmcc.productservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jds.jvmcc.productservice.client.ReviewClient;
import com.jds.jvmcc.productservice.entity.Review;
import com.jds.jvmcc.productservice.service.ReviewConsumerService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-13
 */
@Slf4j
@Service
public class ReviewConsumerServiceImpl implements ReviewConsumerService {

        @Autowired
        private ReviewClient reviewClient;

        @Override
        public List<Review> getReviewsByProduct(String productId) {
                log.info("Getting reviews for product with id: {}", productId);
                return reviewClient.getReviews();
        }

        @Override
        public List<Review> getReviews() {
                log.info("Getting all reviews");
                return reviewClient.getReviews();
        }

}
