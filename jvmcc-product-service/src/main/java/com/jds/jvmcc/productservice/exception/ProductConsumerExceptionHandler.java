package com.jds.jvmcc.productservice.exception;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-14
 */
@Component
public class ProductConsumerExceptionHandler implements FeignHttpExceptionHandler {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Exception handle(Response response) {
        if (response.status() == HttpStatus.BAD_REQUEST.value()) {
            try {
                String responseBody = new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
                ErrorResponse errorResponse = objectMapper.readValue(responseBody, ErrorResponse.class);
                return new ProductConsumerException(errorResponse.getMessage());
            } catch (IOException e) {
                throw new RuntimeException("Error while deserializing the response body");
            }
        }
        return null;
    }
}
