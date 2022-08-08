package com.jds.jvmcc.reviewcrud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jds.jvmcc.reviewcrud.entity.Review;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-05
 */
@Service
public interface ReviewService {

	Review findByProductId(String productId);
	Review findById(Long id);
	List<Review> findAll();
	List<Review> findAllByProductId(String productId);
    Review save(Review review);
	Review update(Review review);
	void delete(Long id);
	void deleteAllForProduct(String productId);
	boolean exists(Long id);
	Integer countByProductId(String productId);
}
