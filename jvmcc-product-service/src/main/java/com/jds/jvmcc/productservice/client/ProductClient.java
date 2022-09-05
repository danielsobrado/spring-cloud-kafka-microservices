package com.jds.jvmcc.productservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jds.jvmcc.productservice.config.ProductClientConfiguration;
import com.jds.jvmcc.productservice.entity.Product;
import com.jds.jvmcc.productservice.exception.HandleFeignException;
import com.jds.jvmcc.productservice.exception.ProductConsumerExceptionHandler;
import com.jds.jvmcc.productservice.fallback.ProductFallback;

import feign.Headers;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-13
 */
@FeignClient(name = "product-client", 
    url = "https://www.adidas.co.uk/api/", 
    configuration = ProductClientConfiguration.class, 
    fallback = ProductFallback.class)
public interface ProductClient {

    @Headers("Content-Type: application/json")
    @GetMapping(value = "/products/{productId}", produces = "application/json")
    @HandleFeignException(ProductConsumerExceptionHandler.class)
    Product getProduct(@PathVariable("productId") String productId);
}