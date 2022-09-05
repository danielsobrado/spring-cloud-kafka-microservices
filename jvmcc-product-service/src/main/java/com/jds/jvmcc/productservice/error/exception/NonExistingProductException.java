package com.jds.jvmcc.productservice.error.exception;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-21
 */
public class NonExistingProductException extends RuntimeException implements ErrorCode {

    public NonExistingProductException(final String message) {
        super("Product not found: "+message);
    }

    @Override
    public String getErrorCode() {
        return "NE-002";
    }
}
