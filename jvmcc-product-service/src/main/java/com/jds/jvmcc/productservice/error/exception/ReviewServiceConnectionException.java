package com.jds.jvmcc.productservice.error.exception;

import java.net.ConnectException;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-29
 */
public class ReviewServiceConnectionException extends ConnectException implements ErrorCode {

    public ReviewServiceConnectionException(final String message) {
        super("Review Service Connection Exception: "+message);
    }

    @Override
    public String getErrorCode() {
        return "RW-CN-001";
    }
}
