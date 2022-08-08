package com.jds.jvmcc.productcrawler.service;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-08
 */
public interface ProductService {

    public Product getProduct(String productId);
    
    public List<Review> getReviews(String productId);
    
    
}
