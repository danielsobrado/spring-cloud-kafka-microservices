package com.jds.jvmcc.productservice.fallback;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jds.jvmcc.productservice.client.ReviewClient;
import com.jds.jvmcc.productservice.entity.Review;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-13
 */
@Component
public class ReviewFallback implements ReviewClient {

    @Override
    public List<Review> getReviews() {
        return Collections.emptyList();
    }

    @Override
    public List<Review> getReviewsByProductId(String productId) {
        return Collections.emptyList();
    }
}
