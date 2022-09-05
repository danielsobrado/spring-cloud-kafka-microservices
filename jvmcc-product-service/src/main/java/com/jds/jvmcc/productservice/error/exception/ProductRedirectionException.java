package com.jds.jvmcc.productservice.error.exception;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-21
 */
public class ProductRedirectionException extends RuntimeException implements ErrorCode {

    public ProductRedirectionException(final String message) {
        super("Product redirection error: "+message);
    }

    @Override
    public String getErrorCode() {
        return "PD-001";
    }
}
