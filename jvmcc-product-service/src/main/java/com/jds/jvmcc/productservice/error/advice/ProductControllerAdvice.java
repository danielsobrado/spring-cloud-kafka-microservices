package com.jds.jvmcc.productservice.error.advice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jds.jvmcc.productservice.error.ProductAppError;
import com.jds.jvmcc.productservice.error.exception.NonExistingProductException;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-21
 */
@RestControllerAdvice
public class ProductControllerAdvice {

    @Value("${product.service.api.version}")
    private String currentApiVersion;

    @ExceptionHandler(NonExistingProductException.class)
    public ResponseEntity<ProductAppError> handleNonExistingProductException(NonExistingProductException ex) {
        final ProductAppError error = new ProductAppError(
                currentApiVersion,
                ex.getErrorCode(),
                "The product does not exist",
                "Product-exceptions",
                "Product not found",
                "No Products found");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
