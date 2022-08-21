package com.jds.jvmcc.reviewservice.error.advice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jds.jvmcc.reviewservice.error.ReviewAppError;
import com.jds.jvmcc.reviewservice.error.exception.NonExistingProductException;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-21
 */
@RestControllerAdvice
public class ReviewControllerAdvice {

    @Value("${review.service.api.version}")
    private String currentApiVersion;

    @ExceptionHandler(NonExistingProductException.class)
    public ResponseEntity<ReviewAppError> handleNonExistingProductException(NonExistingProductException ex) {
        final ReviewAppError error = new ReviewAppError(
                currentApiVersion,
                ex.getErrorCode(),
                "The product does not have reviews",
                "review-exceptions",
                "Product not found in reviews db",
                "No reviews found for this product");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
