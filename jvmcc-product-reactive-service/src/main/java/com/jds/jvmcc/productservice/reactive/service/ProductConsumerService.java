package com.jds.jvmcc.productservice.reactive.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jds.jvmcc.productservice.reactive.entity.Product;
import com.jds.jvmcc.productservice.reactive.entity.Review;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-13
 */
@Service
public interface ProductConsumerService {

    public Product getProduct(String productId);
    
    public List<Review> getReviews(String productId);
    
    public void addReview(String productId, Review review);

    public void updateReview(String productId, String reviewId, Review review);

    public void deleteReview(String productId, String reviewId);

}
