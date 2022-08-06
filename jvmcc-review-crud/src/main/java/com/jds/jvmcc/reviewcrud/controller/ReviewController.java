package com.jds.jvmcc.reviewcrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jds.jvmcc.reviewcrud.entity.Review;
import com.jds.jvmcc.reviewcrud.service.ReviewService;

import io.swagger.annotations.Api;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-05
 */
@RestController("/reviews")
@Api(tags = "Reviews")
public class ReviewController implements ReviewAPI {

    @Autowired
    private ReviewService reviewService;

    @Override
    @GetMapping("/{productId}")
    public Review getReview(@PathVariable String productId) {
        return reviewService.findByProductId(productId);
    }

    @Override
    @GetMapping("/")
    public List<Review> getReviewList() {
        return reviewService.findAll();
    }

}
