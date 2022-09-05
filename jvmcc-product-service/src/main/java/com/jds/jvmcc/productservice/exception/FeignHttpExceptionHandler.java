package com.jds.jvmcc.productservice.exception;

import feign.Response;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-14
 */
public interface FeignHttpExceptionHandler {
    Exception handle(Response response);
}
