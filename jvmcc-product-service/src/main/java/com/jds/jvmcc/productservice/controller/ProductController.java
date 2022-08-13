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
 
        
        return new ProductReviewAgg();

    }

}
