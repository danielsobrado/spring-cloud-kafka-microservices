package com.jds.jvmcc.reviewcrud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jds.jvmcc.reviewcrud.entities.Review;
import com.jds.jvmcc.reviewcrud.services.ReviewService;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-05
 */
@RestController
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/reviews/{productId}")
    public Review getReview(@PathVariable String productId) {
        return reviewService.getReview(productId);
    }

}
