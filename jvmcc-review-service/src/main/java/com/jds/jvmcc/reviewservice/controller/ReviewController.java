package com.jds.jvmcc.reviewservice.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jds.jvmcc.exception.ResourceNotFoundException;
import com.jds.jvmcc.reviewservice.entity.Review;
import com.jds.jvmcc.reviewservice.service.ReviewService;
import com.jds.jvmcc.util.SecurityUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-05
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/review")
public class ReviewController implements ReviewAPI {

    @Autowired
    private ReviewService reviewService;

    @Override
    @GetMapping("/{productId}")
    public List<Review> getReviews(@PathVariable @Pattern(regexp = "[A-Z0-9]{6}") String productId) {
        log.debug("Finding reviews for productId: {}", productId);
        return reviewService.findAllByProductId(productId);
    }

    @Override
    @PostMapping("/save/{productId}")
    @ResponseStatus(HttpStatus.CREATED)
    @Secured("USER")
    public Review createReview(@PathVariable String productId, @Valid @RequestBody Review review) {
        log.debug("Creating review for productId: {}", productId);
        // Escape the productId and comment in the review object
        review.setProductId(SecurityUtil.cleanIt(productId));
        review.setComment(SecurityUtil.cleanIt(review.getComment()));        
        return reviewService.save(review);
    }

    @Override
    @PutMapping("/update/{productId}")
    @Secured("ADMIN")
    public ResponseEntity<Review> updateReview(@PathVariable String productId, @Valid @RequestBody Review review) {
        log.debug("Updating review for productId: {}", productId);
        // Escape the productId and comment in the review object
        review.setProductId(SecurityUtil.cleanIt(productId));
        review.setComment(SecurityUtil.cleanIt(review.getComment()));        
        try {
            reviewService.update(review);
            return ResponseEntity.ok(review);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @DeleteMapping("/delete/{productId}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> deleteProductReviews(@PathVariable @Pattern(regexp = "[A-Z0-9]{6}") String productId) {
        log.debug("Deleting review for productId: {}", productId);
        productId = SecurityUtil.cleanIt(productId);
        reviewService.deleteAllForProduct(productId);
        return new ResponseEntity<>("Reviews deleted successfully for Product!.", HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/delete/{productId}/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> deleteReview(@PathVariable @Pattern(regexp = "[A-Z0-9]{6}") String productId, @PathVariable Long id) {
        log.debug("Deleting review with id: {}", id);
        reviewService.delete(id);
        return new ResponseEntity<>("Review deleted successfully!.", HttpStatus.OK);
    }

    @Override
    @GetMapping("/")
    public List<Review> getReviewList() {
        log.debug("Finding all reviews");
        return reviewService.findAll();
    }

}
