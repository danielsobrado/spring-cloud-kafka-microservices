package com.jds.jvmcc.reviewservice.error.advice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jds.jvmcc.reviewservice.error.ReviewAppError;
import com.jds.jvmcc.reviewservice.error.exception.NonExistingProductException;

import lombok.extern.slf4j.Slf4j;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-21
 */
@Slf4j
@RestControllerAdvice
public class ReviewControllerAdvice {

    @Value("${review.service.api.version}")
    private String currentApiVersion;

    private static final String REVIEW_EXCEPTION = "Review Service Exception";

    @ExceptionHandler(NonExistingProductException.class)
    public ResponseEntity<ReviewAppError> handleNonExistingProductException(NonExistingProductException ex) {
        final ReviewAppError error = new ReviewAppError(
                currentApiVersion,
                ex.getErrorCode(),
                "The product does not have reviews",
                REVIEW_EXCEPTION,
                "Product not found in reviews db",
                "No reviews found for this product");
        log.error(REVIEW_EXCEPTION + " : {}", ex.getMessage());
        // Log Stack Trace
        log.error("Stack Trace: {}", ex);
        
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ReviewAppError> handleException(Exception ex) {
        final ReviewAppError error = new ReviewAppError(
                currentApiVersion,
                "PD-003",
                "An unexpected error has occurred",
                REVIEW_EXCEPTION,
                ex.getLocalizedMessage(),
                ex.getMessage());
        log.error(REVIEW_EXCEPTION + " : {}", ex.getMessage());
        // Log Stack Trace
        log.error("Stack Trace: {}", ex);

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
