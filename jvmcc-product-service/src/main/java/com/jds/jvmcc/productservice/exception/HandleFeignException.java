package com.jds.jvmcc.productservice.exception;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-14
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface HandleFeignException {
    Class<? extends FeignHttpExceptionHandler> value();
}