package com.jds.jvmcc.reviewcrud;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.jds.jvmcc.reviewcrud.entity.Review;
import com.jds.jvmcc.reviewcrud.repository.ReviewRepository;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-08
 */
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReviewRepositoryTests {
    @Autowired
    private ReviewRepository reviewRepository;

    // JUnit test for saveReview() method
    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveReviewTest(){

        Review review = Review.builder()
                .productId("12345")
                .reviewScore((short)3)
                .comment("This is a test comment")
                .build();

        review = reviewRepository.save(review);

        Assertions.assertNotNull(review.getId());

        // Check that the review was saved
        Review savedReview = reviewRepository.findById(review.getId()).orElse(null);
        Assertions.assertNotNull(savedReview);

        // Check that the review was saved with the correct values
        Assertions.assertEquals(review.getProductId(), savedReview.getProductId());
        Assertions.assertEquals(review.getReviewScore(), savedReview.getReviewScore());
        Assertions.assertEquals(review.getComment(), savedReview.getComment());
    }

    // JUnit test for findByProductId() method
    @Test
    @Order(2)
    public void findByProductIdTest(){

        Review review = reviewRepository.findByProductId("12345").orElse(null);

        Assertions.assertNotNull(review);

        // Check that the review was saved with the correct values
        Assertions.assertEquals("12345", review.getProductId());
        Assertions.assertEquals((short)3, review.getReviewScore());
        Assertions.assertEquals("This is a test comment", review.getComment());
    }

    // JUnit test for countByProductId() method
    @Test
    @Order(3)
    public void countByProductIdTest(){

        long count = reviewRepository.countByProductId("12345");

        Assertions.assertEquals(1, count);
    }

    // JUnit test for updateReview() method
    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateReviewTest(){

        Review review = Review.builder()
                .productId("12345")
                .reviewScore((short)3)
                .comment("This is a test comment")
                .build();

        review = reviewRepository.save(review);

        Assertions.assertNotNull(review.getId());

        // Check that the review was saved
        Review savedReview = reviewRepository.findById(review.getId()).orElse(null);
        Assertions.assertNotNull(savedReview);

        // Check that the review was saved with the correct values
        Assertions.assertEquals(review.getProductId(), savedReview.getProductId());
        Assertions.assertEquals(review.getReviewScore(), savedReview.getReviewScore());
        Assertions.assertEquals(review.getComment(), savedReview.getComment());

        // Update the review
        Review updatedReview = Review.builder()
                .productId("12345")
                .reviewScore((short)4)
                .comment("This is an updated test comment")
                .build();

        updatedReview.setId(savedReview.getId());

        updatedReview = reviewRepository.save(updatedReview);

        Assertions.assertNotNull(updatedReview.getId());

        // Check that the review was saved
        Review savedUpdatedReview = reviewRepository.findById(updatedReview.getId()).orElse(null);
        Assertions.assertNotNull(savedUpdatedReview);

        // Check that the review was saved with the correct values
        Assertions.assertEquals(updatedReview.getProductId(), savedUpdatedReview.getProductId());
        Assertions.assertEquals(updatedReview.getReviewScore(), savedUpdatedReview.getReviewScore());
        Assertions.assertEquals(updatedReview.getComment(), savedUpdatedReview.getComment());
    }

    // JUnit test for deleteReview() method
    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteReviewTest(){

        Review review = Review.builder()
                .productId("12345")
                .reviewScore((short)1)
                .comment("This is a test comment to be deleted")
                .build();

        review = reviewRepository.save(review);

        Assertions.assertNotNull(review.getId());

        // Check that the review was saved
        Review savedReview = reviewRepository.findById(review.getId()).orElse(null);
        Assertions.assertNotNull(savedReview);

        // Check that the review was saved with the correct values
        Assertions.assertEquals(review.getProductId(), savedReview.getProductId());
        Assertions.assertEquals(review.getReviewScore(), savedReview.getReviewScore());
        Assertions.assertEquals(review.getComment(), savedReview.getComment());

        // Delete the review
        reviewRepository.delete(savedReview);

        // Check that the review was deleted
        Review deletedReview = reviewRepository.findById(savedReview.getId()).orElse(null);
        Assertions.assertNull(deletedReview);
    }

    // JUnit test for deleteAll() method
    @Test
    @Order(6)
    @Rollback(value = false)
    public void deleteAllByProductId(){

        reviewRepository.deleteAllByProductId("12345");
        
        // Check that the reviews have been deleted
        var deletedReview = reviewRepository.findAllByProductId("12345").orElse(null);
        Assertions.assertTrue(deletedReview.isEmpty());

    }

}
