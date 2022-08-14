package com.jds.jvmcc.productservice.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jds.jvmcc.productservice.entity.ProductReviewAgg;
import com.jds.jvmcc.productservice.entity.Review;
import com.jds.jvmcc.productservice.service.ProductConsumerService;
import com.jds.jvmcc.productservice.service.ProductReviewAggService;
import com.jds.jvmcc.productservice.service.ReviewConsumerService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-14
 * 
 * Calculate the average review score for a product.
 */
@Slf4j
@Service
public class ProductReviewAggServiceImpl implements ProductReviewAggService {

        @Autowired
        private ProductConsumerService productConsumerService;

        @Autowired
        private ReviewConsumerService reviewConsumerService;

        /* 
         * Function to get the product review aggregate.
         * 
         * @param productId The product id.
         * @return The product details and the aggregate of reviews, total number of reviews and average score .
         */
        @Override
        public Optional<ProductReviewAgg> getProductSummary(String productId) {
                log.info("getProductSummary: Getting product with id: {}", productId);
                
                var product = productConsumerService.getProduct(productId);
                // If the product is not found, return null.
                if (product == null) {
                        return Optional.empty();
                }

                var reviews = reviewConsumerService.getReviewsByProduct(productId);
                //  If there are no reviews, return a cero review count and a cero average rating
                if (reviews.isEmpty()) {
                        return Optional.of(new ProductReviewAgg(product, 0d, 0));
                }

                // Build the aggregate summary
                return Optional.of(ProductReviewAgg.builder().productId(productId)
                        .name(product.getName())
                        .model(product.getModel())
                        .reviewCount(reviews.size())
                        .averageReviewScore(reviews.stream().mapToDouble(Review::getRating).average().orElse(0.0))
                        .build());
        }

}
