package com.jds.jvmcc.productservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.jds.jvmcc.productservice.client.ProductClient;
import com.jds.jvmcc.productservice.entity.Product;
import com.jds.jvmcc.productservice.error.exception.NonExistingProductException;
import com.jds.jvmcc.productservice.error.exception.ProductRedirectionException;
import com.jds.jvmcc.productservice.service.ProductConsumerService;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-13
 * 
 *        Retrieve products from the third party service
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "productCache")
public class ProductConsumerServiceImpl implements ProductConsumerService {

        @Autowired
        private ProductClient productClient;

        @Override
        @Cacheable
        public Product getProduct(String productId) {
                log.info("getProduct: Getting product with id: {}", productId);
                // Try to get the product from the third party service
                try {
                        return productClient.getProduct(productId);

                } catch (FeignException e) {
                        log.error("getProduct: Product with id {} not found", productId);
                        // Check if it is a product redirection from detailed message
                        if (e.getMessage().contains("Product redirect")) {
                                throw new ProductRedirectionException("Product redirect" + productId + e.getMessage());
                        } 
                        // Check if the product is not found
                        throw new NonExistingProductException("Product not found: " + productId);
                } catch (Exception e) {
                        log.error("getProduct: Error getting product with id: {}", productId, e);
                        throw new RuntimeException("Error getting product");
                }
        }

}
