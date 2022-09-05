package com.jds.jvmcc.productservice.error.advice;

import java.net.ConnectException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jds.jvmcc.productservice.error.ProductAppError;
import com.jds.jvmcc.productservice.error.exception.NonExistingProductException;
import com.jds.jvmcc.productservice.error.exception.ProductRedirectionException;
import com.jds.jvmcc.productservice.error.exception.ReviewServiceConnectionException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-21
 */
@Slf4j
@RestControllerAdvice
public class ProductControllerAdvice {

    @Value("${product.service.api.version}")
    private String currentApiVersion;

    private static final String PRODUCT_EXCEPTION = "Product Service Exception";

    @ExceptionHandler(NonExistingProductException.class)
    public ResponseEntity<ProductAppError> handleNonExistingProductException(NonExistingProductException ex) {
        final ProductAppError error = new ProductAppError(
                currentApiVersion,
                ex.getErrorCode(),
                "The product does not exist",
                PRODUCT_EXCEPTION,
                "Product not found",
                "No Products found");
        log.error(PRODUCT_EXCEPTION + " : {}", ex.getMessage());
        // Log Stack Trace
        log.error("Stack Trace: {}", ex);
        
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductRedirectionException.class)
    public ResponseEntity<ProductAppError> handleProductRedirectionException(ProductRedirectionException ex) {
        // Extract json from the exception and return it as the response body
        var json = ex.getMessage().substring(ex.getMessage().indexOf("{"), ex.getMessage().lastIndexOf("}") + 1);
        // Parse json and get location
        var jsonObject = new JSONObject(json);
        var location = jsonObject.getString("location");

        final ProductAppError error = new ProductAppError(
                currentApiVersion,
                ex.getErrorCode(),
                "The product has been redirected",
                PRODUCT_EXCEPTION,
                "Product not found",
                "Redirection to " + location);
        log.error(PRODUCT_EXCEPTION + " : {}", ex.getMessage());
        // Log Stack Trace
        log.error("Stack Trace: {}", ex);

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReviewServiceConnectionException.class)
    public ResponseEntity<ProductAppError> handleReviewServiceConnectionException(ReviewServiceConnectionException ex) {
        final ProductAppError error = new ProductAppError(
                currentApiVersion,
                ex.getErrorCode(),
                "The review service is not available",
                PRODUCT_EXCEPTION,
                "Review service not available",
                "Review service is not available");
        log.error(PRODUCT_EXCEPTION + " : {}", ex.getMessage());
        // Log Stack Trace
        log.error("Stack Trace: {}", ex);

        return new ResponseEntity<>(error, HttpStatus.REQUEST_TIMEOUT);
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<ProductAppError> handleReviewServiceConnectionException(ConnectException ex) {
        final ProductAppError error = new ProductAppError(
                currentApiVersion,
                "RW-CN-002",
                "The review service is not available",
                PRODUCT_EXCEPTION,
                "Review service not available",
                "Review service is not available");
        log.error(PRODUCT_EXCEPTION + " : {}", ex.getMessage());
        // Log Stack Trace
        log.error("Stack Trace: {}", ex);

        return new ResponseEntity<>(error, HttpStatus.REQUEST_TIMEOUT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProductAppError> handleException(Exception ex) {
        final ProductAppError error = new ProductAppError(
                currentApiVersion,
                "PD-003",
                "An unexpected error has occurred",
                PRODUCT_EXCEPTION,
                ex.getLocalizedMessage(),
                ex.getMessage());
        log.error(PRODUCT_EXCEPTION + " : {}", ex.getMessage());
        // Log Stack Trace
        log.error("Stack Trace: {}", ex);

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
