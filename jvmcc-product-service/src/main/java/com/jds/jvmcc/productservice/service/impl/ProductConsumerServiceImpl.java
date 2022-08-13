package com.jds.jvmcc.productservice.service.impl;

import java.util.List;

import com.jds.jvmcc.productservice.entity.Product;
import com.jds.jvmcc.productservice.service.ProductConsumerService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-08
 */
@Slf4j
public class ProductConsumerServiceImpl implements ProductConsumerService {
    
    private final WebClient webClient;

    public ProductConsumerServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }  

    public Product getProduct(String productId) {
        log.info("Getting product with id: {}", productId);
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/products/{productId}")
                        .build(productId))
                .retrieve()
                .bodyToMono(Product.class)
                .block();
    }

    public List<Review> getReviews(String productId) {
        log.info("Getting reviews for product with id: {}", productId);
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/products/{productId}/reviews")
                        .build(productId))
                .retrieve()
                .bodyToFlux(Review.class)
                .collectList()
                .block();
    }

    public void addReview(String productId, Review review) {
        log.info("Adding review for product with id: {}", productId);
        webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/products/{productId}/reviews")
                        .build(productId))
                .body(Mono.just(review), Review.class)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public void deleteReview(String productId, String reviewId) {
        log.info("Deleting review for product with id: {}", productId);
        webClient.delete()
                .uri(uriBuilder -> uriBuilder
                        .path("/products/{productId}/reviews/{reviewId}")
                        .build(productId, reviewId))
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }

    public void updateReview(String productId, String reviewId, Review review) {
        log.info("Updating review for product with id: {}", productId);
        webClient.put()
                .uri(uriBuilder -> uriBuilder
                        .path("/products/{productId}/reviews/{reviewId}")
                        .build(productId, reviewId))
                .body(Mono.just(review), Review.class)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
   
}
