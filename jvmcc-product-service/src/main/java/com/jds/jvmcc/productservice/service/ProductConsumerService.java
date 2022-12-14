package com.jds.jvmcc.productservice.service;

import com.jds.jvmcc.productservice.entity.Product;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-08
 */
public interface ProductConsumerService {

    public Product getProduct(String productId);

}
