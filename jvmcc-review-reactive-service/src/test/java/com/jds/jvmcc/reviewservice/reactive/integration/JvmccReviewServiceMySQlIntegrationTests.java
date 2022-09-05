package com.jds.jvmcc.reviewservice.reactive.integration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import com.jds.jvmcc.reviewservice.reactive.entity.Review;
import com.jds.jvmcc.reviewservice.reactive.repository.ReviewRepository;
import com.jds.jvmcc.reviewservice.reactive.service.impl.ReviewServiceImpl;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-13
 */
@ActiveProfiles("test")
@SpringBootTest(classes = {com.jds.jvmcc.reviewservice.reactive.JvmccReviewServiceApplication.class, 
	com.jds.jvmcc.reviewservice.reactive.service.impl.ReviewServiceImpl.class,
	com.jds.jvmcc.reviewservice.reactive.repository.ReviewRepository.class})
@EnableAutoConfiguration
class JvmccReviewServiceMySQlIntegrationTests {

	static final String PRODUCT_ID = "12345";

	@Autowired
    private ReviewServiceImpl reviewService;

    @Autowired
    private ReviewRepository reviewRepository;

    @DisplayName("JUnit test for saveReview() method")
    @Test
    @Order(1)
    public void saveReviewTest(){

        Review review = Review.builder()
                .productId(PRODUCT_ID)
                .reviewScore((short)3)
                .comment("This is a test comment")
                .build();

        review = reviewService.save(review);

        Assertions.assertNotNull(review.getId());
        Assertions.assertTrue(review.getId() > 0);
        Assertions.assertEquals(PRODUCT_ID, review.getProductId());

        // Check that the review was saved
        var savedReview = reviewService.findById(review.getId()).orElse(null);
        Assertions.assertNotNull(savedReview);
        Assertions.assertEquals(review.getId(), savedReview.getId());

        // Check that the review was saved with the correct values
        Assertions.assertEquals(review.getProductId(), savedReview.getProductId());
        Assertions.assertEquals(review.getReviewScore(), savedReview.getReviewScore());
        Assertions.assertEquals(review.getComment(), savedReview.getComment());
    }

    @DisplayName("JUnit test for findByProductId() method")
    @Test
    @Order(2)
    public void findByProductIdTest(){

        var review = reviewService.findByProductId(PRODUCT_ID).orElse(null);

        Assertions.assertNotNull(review);

        // Check that the review was saved with the correct values
        Assertions.assertEquals(PRODUCT_ID, review.getProductId());
        Assertions.assertEquals((short)3, review.getReviewScore());
        Assertions.assertEquals("This is a test comment", review.getComment());
    }

    @DisplayName("JUnit test for countByProductId() method")
    @Test
    @Order(3)
    public void countByProductIdTest(){

        var count = reviewService.countByProductId(PRODUCT_ID);

        Assertions.assertEquals(1, count);
    }

    @DisplayName("JUnit test for updateReview() method")
    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateReviewTest(){

        var review = Review.builder()
                .productId(PRODUCT_ID)
                .reviewScore((short)3)
                .comment("This is a test comment")
                .build();

        review = reviewService.save(review);

        Assertions.assertNotNull(review.getId());

        // Check that the review was saved
        var savedReview = reviewService.findById(review.getId()).orElse(null);
        Assertions.assertNotNull(savedReview);

        // Check that the review was saved with the correct values
        Assertions.assertEquals(review.getProductId(), savedReview.getProductId());
        Assertions.assertEquals(review.getReviewScore(), savedReview.getReviewScore());
        Assertions.assertEquals(review.getComment(), savedReview.getComment());

        // Update the review
        var updatedReview = Review.builder()
                .productId(PRODUCT_ID)
                .reviewScore((short)4)
                .comment("This is an updated test comment")
                .build();

        updatedReview.setId(savedReview.getId());

        updatedReview = reviewService.save(updatedReview);

        Assertions.assertNotNull(updatedReview.getId());

        // Check that the review was saved
        var savedUpdatedReview = reviewService.findById(updatedReview.getId()).orElse(null);
        Assertions.assertNotNull(savedUpdatedReview);

        // Check that the review was saved with the correct values
        Assertions.assertEquals(updatedReview.getProductId(), savedUpdatedReview.getProductId());
        Assertions.assertEquals(updatedReview.getReviewScore(), savedUpdatedReview.getReviewScore());
        Assertions.assertEquals(updatedReview.getComment(), savedUpdatedReview.getComment());
    }

    @DisplayName("JUnit test for deleteReview() method")
    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteReviewTest(){

        var review = Review.builder()
                .productId(PRODUCT_ID)
                .reviewScore((short)1)
                .comment("This is a test comment to be deleted")
                .build();

        review = reviewService.save(review);

        Assertions.assertNotNull(review.getId());

        // Check that the review was saved
        var savedReview = reviewService.findById(review.getId()).orElse(null);
        Assertions.assertNotNull(savedReview);

        // Check that the review was saved with the correct values
        Assertions.assertEquals(review.getProductId(), savedReview.getProductId());
        Assertions.assertEquals(review.getReviewScore(), savedReview.getReviewScore());
        Assertions.assertEquals(review.getComment(), savedReview.getComment());

        // Delete the review
        reviewRepository.delete(savedReview);

        // Check that the review was deleted
        var deletedReview = reviewService.findById(savedReview.getId()).orElse(null);
        Assertions.assertNull(deletedReview);
    }

    @DisplayName("JUnit test for deleteAll() method")
    @Test
    @Order(6)
    @Rollback(value = false)
    public void deleteAllByProductId(){

        reviewRepository.deleteAllByProductId(PRODUCT_ID);
        
        // Check that the reviews have been deleted
        var deletedReview = reviewRepository.findAllByProductId(PRODUCT_ID).orElse(null);
        Assertions.assertTrue(deletedReview.isEmpty());

    }

}
