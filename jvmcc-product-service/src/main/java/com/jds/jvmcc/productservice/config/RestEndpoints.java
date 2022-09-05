package com.jds.jvmcc.productservice.config;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-08
 */

public class RestEndpoints {

    // Make the class static so that it can be used without instantiation
    private RestEndpoints() {
    }

    @Value("${product.url}")
    public static final String PRODUCT_ENDPOINT = "https://www.adidas.co.uk/api/products/"; 

}
