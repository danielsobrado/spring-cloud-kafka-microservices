package com.jds.jvmcc.productservice.fallback;

import org.springframework.stereotype.Component;

import com.jds.jvmcc.productservice.client.ProductClient;
import com.jds.jvmcc.productservice.entity.Product;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-13
 */
@Component
public class ProductFallback implements ProductClient {

    @Override
    public Product getProduct(String productId) {
        return null;
    }

}
