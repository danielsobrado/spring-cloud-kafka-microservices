package com.jds.jvmcc.reviewcrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jds.jvmcc.reviewcrud.entity.Review;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-05
 */
@Service
public interface ReviewService {

    Review save(Review review);

	Review findByProductId(String productId);

	List<Review> findAll();

}
