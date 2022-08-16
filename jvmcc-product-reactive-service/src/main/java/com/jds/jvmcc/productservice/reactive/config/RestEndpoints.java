package com.jds.jvmcc.productservice.reactive.config;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-13
 */
public class RestEndpoints {

    // Make the class static so that it can be used without instantiation
    private RestEndpoints() {
    }

    @Value("${product.url}")
    public static final String PRODUCT_ENDPOINT = "https://www.adidas.co.uk/api/products/"; 

}
