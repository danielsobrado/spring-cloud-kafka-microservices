package com.jds.jvmcc.productservice.service;

import java.util.List;

import com.jds.jvmcc.productservice.entity.Review;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-08
 */
public interface ReviewConsumerService {

    public List<Review> getReviewsByProduct(String productId);
    public List<Review> getReviews();

}
