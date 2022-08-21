package com.jds.jvmcc.productservice.error.advice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jds.jvmcc.productservice.error.ProductAppError;
import com.jds.jvmcc.productservice.error.exception.NonExistingProductException;
import com.jds.jvmcc.productservice.error.exception.ProductRedirectionException;

import org.json.*;

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
                "Product-exceptions",
                "Product not found",
                "Redirection to " + location);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
