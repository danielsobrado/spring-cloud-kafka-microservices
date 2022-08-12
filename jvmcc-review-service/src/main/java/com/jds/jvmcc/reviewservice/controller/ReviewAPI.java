package com.jds.jvmcc.reviewservice.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.springframework.http.ResponseEntity;

import com.jds.jvmcc.reviewservice.entity.Review;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-06
 */
public interface ReviewAPI {

	@Operation(summary = "Get All Reviews", description = "Get list of reviews in the System")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description  = "Success"), 
			@ApiResponse(responseCode = "401", description  = "Not authorized!"), 
			@ApiResponse(responseCode = "403", description  = "Forbidden!"),
			@ApiResponse(responseCode = "404", description  = "Not found!") })
	public List<Review> getReviewList();

	@Operation(summary = "Get Review", description = "Get review by product ID")
	public ResponseEntity<Review> getReview(@Pattern(regexp = "[A-Z0-9]{6}") String productId);

	@Operation(summary = "Create Review", description = "Create a new review for a product")
	public Review createReview(String productId, @Valid Review review);

	@Operation(summary = "Update Review", description = "Update an existing review for a product")
	public ResponseEntity<Review> updateReview(String productId, @Valid Review review);

	@Operation(summary = "Delete All Reviews for a Product", description = "Delete all reviews for a product")
	public ResponseEntity<String> deleteProductReviews(@Pattern(regexp = "[A-Z0-9]{6}") String productId);

	@Operation(summary = "Delete Review", description = "Delete an existing review for a product")
	public ResponseEntity<String> deleteReview(@Pattern(regexp = "[A-Z0-9]{6}")  String productId, Long id);

}
