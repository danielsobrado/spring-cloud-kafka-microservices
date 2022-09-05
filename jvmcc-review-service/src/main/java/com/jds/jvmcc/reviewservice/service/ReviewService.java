package com.jds.jvmcc.reviewservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jds.jvmcc.exception.ResourceNotFoundException;
import com.jds.jvmcc.reviewservice.entity.Review;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-05
 */
@Service
@Transactional
public interface ReviewService {

	@Transactional(readOnly = true)
	Optional<Review> findById(Long id);
	@Transactional(readOnly = true)
	List<Review> findAll();
	@Transactional(readOnly = true)
	List<Review> findAllByProductId(String productId);
    Review save(Review review);
	Review update(Review review) throws ResourceNotFoundException;
	void delete(Long id);
	void deleteAllForProduct(String productId);
	boolean exists(Long id);
	Integer countByProductId(String productId);
}
