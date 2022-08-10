package com.jds.jvmcc.reviewservice.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.jds.jvmcc.reviewservice.JvmccReviewServiceApplication;
import com.jds.jvmcc.reviewservice.entity.Review;
import com.jds.jvmcc.reviewservice.service.ReviewService;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-09
 */
// @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
// @ActiveProfiles("test")
// @SpringBootTest(
//     classes = {JvmccReviewServiceApplication.class,
//             com.jds.jvmcc.reviewservice.repository.ReviewRepositoryTests.class, 
//             com.jds.jvmcc.reviewservice.repository.ReviewRepository.class,
//             com.jds.jvmcc.reviewservice.service.ReviewService.class},
//     webEnvironment = WebEnvironment.RANDOM_PORT
// )
@TestPropertySource(properties ={"spring.config.location=classpath:application-test.yml"})
@SpringBootTest(classes = JvmccReviewServiceApplication.class)
public class ReviewRepositoryTests {

    static final String PRODUCT_ID = "12345";

    @Autowired
    private ReviewService reviewService;

    // @Autowired
    // private ReviewRepository reviewRepository;

    // @Bean
    // ServletWebServerFactory servletWebServerFactory() {
    //     return new TomcatServletWebServerFactory();
    // }

    // void setup(){
    //     reviewService.deleteAllByProductId(PRODUCT_ID);
    // }

    // JUnit test for saveReview() method
    @Test
    // @Order(1)
    // @Rollback(value = false)
    public void saveReviewTest(){

        Review review = Review.builder()
                .productId(PRODUCT_ID)
                .reviewScore((short)3)
                .comment("This is a test comment")
                .build();

        // review = reviewService.save(review);

        //assertNotNull(review.getId());
        // assertTrue(review.getId() > 0);
        // assertEquals(PRODUCT_ID, review.getProductId());

        // Check that the review was saved
        // Review savedReview = reviewService.findById(review.getId()).orElse(null);
        // Assertions.assertNotNull(savedReview);
        // assertEquals(review.getId(), savedReview.getId());

        // Check that the review was saved with the correct values
        // Assertions.assertEquals(review.getProductId(), savedReview.getProductId());
        // Assertions.assertEquals(review.getReviewScore(), savedReview.getReviewScore());
        // Assertions.assertEquals(review.getComment(), savedReview.getComment());
    }

    // JUnit test for findByProductId() method
    @Test
    // @Order(2)
    public void findByProductIdTest(){

        // Review review = reviewService.findByProductId(PRODUCT_ID).orElse(null);

        // Assertions.assertNotNull(review);

        // Check that the review was saved with the correct values
        // Assertions.assertEquals(PRODUCT_ID, review.getProductId());
        // Assertions.assertEquals((short)3, review.getReviewScore());
        // Assertions.assertEquals("This is a test comment", review.getComment());
    }

    // JUnit test for countByProductId() method
    @Test
    // @Order(3)
    public void countByProductIdTest(){

        // long count = reviewService.countByProductId(PRODUCT_ID);

        // Assertions.assertEquals(1, count);
    }

    // JUnit test for updateReview() method
    @Test
    // @Order(4)
    @Rollback(value = false)
    public void updateReviewTest(){

        Review review = Review.builder()
                .productId(PRODUCT_ID)
                .reviewScore((short)3)
                .comment("This is a test comment")
                .build();

        // review = reviewService.save(review);

        // Assertions.assertNotNull(review.getId());

        // Check that the review was saved
        // Review savedReview = reviewService.findById(review.getId()).orElse(null);
        // Assertions.assertNotNull(savedReview);

        // Check that the review was saved with the correct values
        // Assertions.assertEquals(review.getProductId(), savedReview.getProductId());
        // Assertions.assertEquals(review.getReviewScore(), savedReview.getReviewScore());
        // Assertions.assertEquals(review.getComment(), savedReview.getComment());

        // Update the review
        Review updatedReview = Review.builder()
                .productId(PRODUCT_ID)
                .reviewScore((short)4)
                .comment("This is an updated test comment")
                .build();

        // updatedReview.setId(savedReview.getId());

        // updatedReview = reviewService.save(updatedReview);

        // Assertions.assertNotNull(updatedReview.getId());

        // Check that the review was saved
        // Review savedUpdatedReview = reviewService.findById(updatedReview.getId()).orElse(null);
        // Assertions.assertNotNull(savedUpdatedReview);

        // Check that the review was saved with the correct values
        // Assertions.assertEquals(updatedReview.getProductId(), savedUpdatedReview.getProductId());
        // Assertions.assertEquals(updatedReview.getReviewScore(), savedUpdatedReview.getReviewScore());
        // Assertions.assertEquals(updatedReview.getComment(), savedUpdatedReview.getComment());
    }

    // JUnit test for deleteReview() method
    @Test
    // @Order(5)
    @Rollback(value = false)
    public void deleteReviewTest(){

        Review review = Review.builder()
                .productId(PRODUCT_ID)
                .reviewScore((short)1)
                .comment("This is a test comment to be deleted")
                .build();

        // review = reviewService.save(review);

        // Assertions.assertNotNull(review.getId());

        // Check that the review was saved
        // Review savedReview = reviewService.findById(review.getId()).orElse(null);
        // Assertions.assertNotNull(savedReview);

        // Check that the review was saved with the correct values
        // Assertions.assertEquals(review.getProductId(), savedReview.getProductId());
        // Assertions.assertEquals(review.getReviewScore(), savedReview.getReviewScore());
        // Assertions.assertEquals(review.getComment(), savedReview.getComment());

        // Delete the review
        // reviewRepository.delete(savedReview);

        // Check that the review was deleted
        // Review deletedReview = reviewService.findById(savedReview.getId()).orElse(null);
        // Assertions.assertNull(deletedReview);
    }

    // JUnit test for deleteAll() method
    @Test
    // @Order(6)
    @Rollback(value = false)
    public void deleteAllByProductId(){

        // reviewRepository.deleteAllByProductId(PRODUCT_ID);
        
        // Check that the reviews have been deleted
        // var deletedReview = reviewRepository.findAllByProductId(PRODUCT_ID).orElse(null);
        // Assertions.assertTrue(deletedReview.isEmpty());

    }

}
