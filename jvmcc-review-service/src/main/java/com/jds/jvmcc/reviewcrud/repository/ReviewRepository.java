package com.jds.jvmcc.reviewcrud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jds.jvmcc.reviewcrud.entity.Review;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-06
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {

    Optional<Review> findByProductId(String productId);
}