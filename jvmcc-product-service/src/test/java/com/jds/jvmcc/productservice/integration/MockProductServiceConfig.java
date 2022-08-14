package com.jds.jvmcc.productservice.integration;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jds.jvmcc.productservice.entity.Product;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-14
 */
@Configuration
@RestController
@ActiveProfiles("eureka-test")
public class MockProductServiceConfig {

    private static final String PRODUCT_ID = "M20324";
    private static final String PRODUCT_NAME = "Stan Smith Shoes";
    private static final String PRODUCT_MODEL = "ION05";

    @RequestMapping("/product")
    public List<Product> getProducts() {
        return Collections.singletonList(new Product(PRODUCT_ID, PRODUCT_MODEL, PRODUCT_NAME));
    }
}
