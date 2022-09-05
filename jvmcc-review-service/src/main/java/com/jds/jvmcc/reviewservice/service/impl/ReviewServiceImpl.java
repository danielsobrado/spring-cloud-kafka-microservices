package com.jds.jvmcc.reviewservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.jds.jvmcc.exception.ResourceNotFoundException;
import com.jds.jvmcc.reviewservice.entity.Review;
import com.jds.jvmcc.reviewservice.error.exception.NonExistingProductException;
import com.jds.jvmcc.reviewservice.repository.ReviewRepository;
import com.jds.jvmcc.reviewservice.service.ReviewService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-05
 * 
 * CRUDs for reviews.
 * Return the reviews for a product and cache them to minimize the number of calls to the database.
 */
@Slf4j
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
	private ReviewRepository reviewRepository;

    public static final String TABLE_NAME = "reviews";

    public Optional<Review> findById(Long id) {
        log.debug("Finding review with id: {}", id);
        return reviewRepository.findById(id);
    }

    public List<Review> findAll() {
        log.debug("Finding all reviews");
        return reviewRepository.findAll();
    }

    // Our Product service is only interested in the reviews for a specific product.
    // Therefore, we can cache the reviews for a specific product.
    @Cacheable(value = "reviews", key = "#productId")
    public List<Review> findAllByProductId(String productId) throws ResourceNotFoundException {
        log.debug("Finding all reviews for productId: {}", productId);
        return reviewRepository.findAllByProductId(productId).orElseThrow(() -> new NonExistingProductException(productId));
    }

    public Review save(Review review) {
        log.debug("Saving review: {}", review);

        // Invalidate cache
        try {
            cacheManager.getCache(TABLE_NAME).evictIfPresent(review.getProductId());
        } catch (NullPointerException e) {
            log.warn("Cache not found: "+e.getMessage());           
        }

        return reviewRepository.save(review);
    }

    public Review update(Review review) {
        log.debug("Updating review: {}", review);

        // Check if review exists
        if (!reviewRepository.existsById(review.getId())) {
            throw new ResourceNotFoundException(TABLE_NAME, "id", review.getId());
        }

        // Invalidate cache
        try {
            cacheManager.getCache(TABLE_NAME).evictIfPresent(review.getProductId());
        } catch (NullPointerException e) {
            log.warn("Cache not found: "+e.getMessage());           
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
        
        // Invalidate cache
        try {
            // Get Review
            Optional<Review> review = reviewRepository.findById(id);
            cacheManager.getCache(TABLE_NAME).evictIfPresent(review.get().getProductId());
        } catch (NullPointerException e) {
            log.warn("Cache not found: "+e.getMessage());           
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

        // Invalidate cache
        try {
            cacheManager.getCache(TABLE_NAME).evictIfPresent(productId);
        } catch (NullPointerException e) {
            log.warn("Cache not found: "+e.getMessage());           
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
