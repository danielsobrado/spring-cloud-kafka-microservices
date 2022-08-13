package com.jds.jvmcc.productservice.config;

import com.jds.jvmcc.productservice.exception.BadRequestException;
import com.jds.jvmcc.productservice.exception.NotFoundException;

import feign.Response;
import feign.codec.ErrorDecoder;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-13
 */
public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {

        switch (response.status()){
            case 400:
                return new BadRequestException();
            case 404:
                return new NotFoundException();
            default:
                return new Exception("Generic error");
        }
    }
}