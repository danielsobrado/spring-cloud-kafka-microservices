package com.jds.jvmcc.productservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.jds.jvmcc.productservice.client.ProductClient;
import com.jds.jvmcc.productservice.entity.Product;
import com.jds.jvmcc.productservice.service.ProductConsumerService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-13
 * 
 * Retrieve products from the third party service
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
                return productClient.getProduct(productId);
        }

}
