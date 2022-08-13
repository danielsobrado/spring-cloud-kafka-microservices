package com.jds.jvmcc.productservice.client;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jds.jvmcc.productservice.entity.Review;
import com.jds.jvmcc.productservice.service.ReviewConsumerService;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-05
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReviewManualTest {

    private static final String PRODUCT_ID = "M20324";

    @Autowired
    private ReviewConsumerService reviewConsumerService;

    @Test
    public void whenSpringContextIsBootstrapped_thenNoExceptions() {
    }

    @Test
    public void whenGetPosts_thenListPostSizeGreaterThanZero() {

        List<Review> reviews = reviewConsumerService.getReviews();

        assertFalse(reviews.isEmpty());
    }

    // @Test
    // public void whenGetPostWithId_thenPostExist() {

    //     Post post = reviewConsumerService.getPostById(1L);

    //     assertNotNull(post);
    // }

}