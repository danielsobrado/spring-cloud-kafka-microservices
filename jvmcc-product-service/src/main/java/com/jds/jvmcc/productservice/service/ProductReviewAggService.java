package com.jds.jvmcc.productservice.service;

import java.util.Optional;

import com.jds.jvmcc.productservice.entity.ProductReviewAgg;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-14
 */
public interface ProductReviewAggService {

    public Optional<ProductReviewAgg> getProductSummary(String productId);

}
