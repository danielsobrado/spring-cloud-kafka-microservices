package com.jds.jvmcc.reviewcrud.controller;

import java.util.List;

import com.jds.jvmcc.reviewcrud.entity.Review;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-06
 */
@Api
public interface ReviewAPI {

	@ApiOperation(value = "Get list of reviews in the System ", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 401, message = "Not authorized!"), @ApiResponse(code = 403, message = "Forbidden!"),
			@ApiResponse(code = 404, message = "Not found!") })
	public List<Review> getReviewList();

	@ApiOperation(value = "Get product by product ID", response = Review.class)
	public Review getReview(String productId);

}
