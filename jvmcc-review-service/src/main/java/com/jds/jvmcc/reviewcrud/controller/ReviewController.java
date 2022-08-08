package com.jds.jvmcc.reviewcrud.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jds.jvmcc.reviewcrud.entity.Review;
import com.jds.jvmcc.reviewcrud.service.ReviewService;
import com.jds.jvmcc.reviewcrud.util.SecurityUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-05
 */
@Slf4j
@RestController("/reviews")
public class ReviewController implements ReviewAPI {

    @Autowired
    private ReviewService reviewService;

    @Override
    @GetMapping("/{productId}")
    public Review getReview(@PathVariable String productId) {
        log.debug("Finding review for productId: {}", productId);
        return reviewService.findByProductId(productId);
    }

    @Override
    @PostMapping("/{productId}")
    public Review createReview(@PathVariable String productId, @Valid @RequestBody Review review) {
        log.debug("Creating review for productId: {}", productId);
        // Escape the productId and comment in the review object
        review.setProductId(SecurityUtil.cleanIt(productId));
        review.setComment(SecurityUtil.cleanIt(review.getComment()));        
        return reviewService.save(review);
    }

    @Override
    @PutMapping("/{productId}")
    public Review updateReview(@PathVariable String productId, @Valid @RequestBody Review review) {
        log.debug("Updating review for productId: {}", productId);
        // Escape the productId and comment in the review object
        review.setProductId(SecurityUtil.cleanIt(productId));
        review.setComment(SecurityUtil.cleanIt(review.getComment()));        
        return reviewService.update(review);
    }

    @Override
    @DeleteMapping("/{productId}")
    public void deleteReview(@PathVariable String productId) {
        log.debug("Deleting review for productId: {}", productId);
        reviewService.deleteAllForProduct(productId);
    }

    @Override
    @DeleteMapping("/{productId}/{id}")
    public void deleteReview(@PathVariable String productId, @PathVariable Long id) {
        log.debug("Deleting review with id: {}", id);
        reviewService.delete(id);
    }

    @Override
    @GetMapping("/list")
    public List<Review> getReviewList() {
        log.debug("Finding all reviews");
        return reviewService.findAll();
    }

}
