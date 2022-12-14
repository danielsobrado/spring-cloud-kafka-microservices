package com.jds.jvmcc.reviewservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jds.jvmcc.reviewservice.entity.Review;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-06
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