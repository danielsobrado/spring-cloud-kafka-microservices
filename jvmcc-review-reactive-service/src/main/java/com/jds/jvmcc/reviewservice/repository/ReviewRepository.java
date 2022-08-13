package com.jds.jvmcc.reviewservice.reactive.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jds.jvmcc.reviewservice.reactive.entity.Review;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-13
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {

    Optional<Review> findByProductId(String productId);
    Optional<List<Review>> findAllByProductId(String productId);
    void deleteById(Long id);
    void deleteAllByProductId(String productId);
    boolean existsById(Long id);
    Integer countByProductId(String productId);
    Optional<Review> findById(Long id);
}