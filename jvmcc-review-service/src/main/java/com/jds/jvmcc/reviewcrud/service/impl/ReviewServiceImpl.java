package com.jds.jvmcc.reviewcrud.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jds.jvmcc.reviewcrud.entity.Review;
import com.jds.jvmcc.reviewcrud.exception.ResourceNotFoundException;
import com.jds.jvmcc.reviewcrud.repository.ReviewRepository;
import com.jds.jvmcc.reviewcrud.service.ReviewService;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-05
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
	private ReviewRepository reviewRepository;

    /**
     * @param review
     * @return
     */
    public Review findByProductId(String productId) {
        return reviewRepository.findByProductId(productId).orElseThrow(() -> new ResourceNotFoundException("Review", "productId", productId));
    }

    /**
     * @return
     */
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    /**
     * @param review
     * @return
     */
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

}
