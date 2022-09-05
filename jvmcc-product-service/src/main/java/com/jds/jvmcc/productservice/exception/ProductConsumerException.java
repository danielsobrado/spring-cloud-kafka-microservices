package com.jds.jvmcc.productservice.exception;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-14
 */
public class ProductConsumerException extends RuntimeException {
    public ProductConsumerException(String message) {
        super(message);
    }
}