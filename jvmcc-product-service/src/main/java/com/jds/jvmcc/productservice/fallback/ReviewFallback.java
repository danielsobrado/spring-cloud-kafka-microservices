package com.jds.jvmcc.productservice.fallback;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jds.jvmcc.productservice.client.ReviewClient;
import com.jds.jvmcc.productservice.entity.Post;
import com.jds.jvmcc.productservice.entity.Review;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-13
 */
@Component
public class ReviewFallback implements ReviewClient {

    @Override
    public List<Review> getReviews() {
        return Collections.emptyList();
    }

    // @Override
    // public Review getReviewsByProductId(String productId) {
    //     return null;
    // }
}
