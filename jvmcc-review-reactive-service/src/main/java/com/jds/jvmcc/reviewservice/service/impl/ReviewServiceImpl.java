package com.jds.jvmcc.reviewservice.reactive.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jds.jvmcc.exception.ResourceNotFoundException;
import com.jds.jvmcc.reviewservice.reactive.entity.Review;
import com.jds.jvmcc.reviewservice.reactive.repository.ReviewRepository;
import com.jds.jvmcc.reviewservice.reactive.service.ReviewService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-13
 */
@Slf4j
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
	private ReviewRepository reviewRepository;

    public static final String TABLE_NAME = "Review";

    public Optional<Review> findById(Long id) {
        log.debug("Finding review with id: {}", id);
        return reviewRepository.findById(id);
    }

    public Optional<Review> findByProductId(String productId) {
        log.debug("Finding review for productId: {}", productId);
        return reviewRepository.findByProductId(productId);
    }

    public List<Review> findAll() {
        log.debug("Finding all reviews");
        return reviewRepository.findAll();
    }

    public List<Review> findAllByProductId(String productId) throws ResourceNotFoundException {
        log.debug("Finding all reviews for productId: {}", productId);
        return reviewRepository.findAllByProductId(productId).orElseThrow(() -> new ResourceNotFoundException(TABLE_NAME, "productId", productId));
    }

    public Review save(Review review) {
        log.debug("Saving review: {}", review);
        return reviewRepository.save(review);
    }

    public Review update(Review review) {
        log.debug("Updating review: {}", review);
        // Check if review exists
        if (!reviewRepository.existsById(review.getId())) {
            throw new ResourceNotFoundException(TABLE_NAME, "id", review.getId());
        }
        return reviewRepository.save(review);
    }

    public void delete(Long id) {
        log.debug("Deleting review with id: {}", id);
        // Check if review exists
        if (!reviewRepository.existsById(id)) {
            log.warn("Review with id: {} does not exist", id);
            throw new ResourceNotFoundException(TABLE_NAME, "id", id);
        }
        reviewRepository.deleteById(id);
    }

    public void deleteAllForProduct(String productId) {
        log.debug("Deleting all reviews for productId: {}", productId);
        // Check if it has any
        var reviewsCountByProduct = reviewRepository.countByProductId(productId);
        if ( reviewsCountByProduct == 0) {
            log.warn("No reviews found for product id: {}", productId);
            throw new ResourceNotFoundException(TABLE_NAME, "productId", productId);
        } 
        log.info("Deleting {} reviews for productId: {}", reviewsCountByProduct, productId);
        reviewRepository.deleteAllByProductId(productId);
    }

    public boolean exists(Long id) {
        log.debug("Checking if review with id: {} exists", id);
        return reviewRepository.existsById(id);
    }

    public Integer countByProductId(String productId) {
        log.debug("Counting reviews for productId: {}", productId);
        return reviewRepository.countByProductId(productId);
    }

}
