package com.jds.jvmcc.reviewservice.reactive.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.reactive.ReactiveRepository;

import com.jds.jvmcc.reviewservice.reactive.entity.Review;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-13
 */
@Repository
public interface ReviewRepository extends ReactiveRepository<Review, String> {

    Flux<Review> findAllByProductId(String productId);
}