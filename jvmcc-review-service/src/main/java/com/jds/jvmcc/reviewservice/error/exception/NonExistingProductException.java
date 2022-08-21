package com.jds.jvmcc.reviewservice.error.exception;

import com.jds.jvmcc.error.ErrorCode;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-21
 */
public class NonExistingProductException extends RuntimeException implements ErrorCode {

    public NonExistingProductException(final String message) {
        super("Product not found: "+message);
    }

    @Override
    public String getErrorCode() {
        return "NE-001";
    }
}
