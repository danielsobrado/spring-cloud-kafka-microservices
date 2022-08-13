package com.jds.jvmcc.productservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jds.jvmcc.productservice.entity.ProductReviewAgg;

import lombok.extern.slf4j.Slf4j;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-08
 */
@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {

    @Value("${product.url:Not found default}")
    private String message;

    
    @GetMapping("/url")
    public String getUrl() {
        log.info("Getting url from Configuration Server");
        return message;
    }

    @GetMapping("/{product_id}")
    public ProductReviewAgg getProduct(@PathVariable("product_id") String productId) {
        log.info("Getting product {}", productId);
        // Load some user data asynchronously, e.g. from a DB:
/*         Mono<BaseUserInfo> userInfo = getBaseUserInfo(id);

        // Load user data with WebClient from a separate API:
        Mono<ReviewSubscription> productSubscription = client.get()
            .uri("http://subscription-service/api/user/" + productId)
            .retrieve()
            .bodyToMono(ProductSubscription.class);

        // Combine the monos: when they are both done, take the
        // data from each and combine it into a ProductReviewAgg object.
        Mono<Product> product = productInfo
            .zipWith(productSubscription)
            .map((tuple) -> new Product(tuple.getT1(), tuple.getT2());

        // The resulting mono of combined data can be returned immediately,
        // without waiting or blocking, and WebFlux will handle sending
        // the response later, once all the data is ready: */
        // return product;
        return new ProductReviewAgg();

    }

}
