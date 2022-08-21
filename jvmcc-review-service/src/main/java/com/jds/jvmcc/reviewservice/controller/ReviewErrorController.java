package com.jds.jvmcc.reviewservice.controller;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-21
 */
@RestController
@RequestMapping({ReviewErrorController.ERROR_PATH})
public class ReviewErrorController extends AbstractErrorController {

    @Value("${review.service.error.path:/error}")
    static final String ERROR_PATH = "/error";

    public ReviewErrorController(final ErrorAttributes errorAttributes) {
        super(errorAttributes, Collections.emptyList());
    }

    @RequestMapping
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = this.getErrorAttributes(request, ErrorAttributeOptions.defaults());
        HttpStatus status = this.getStatus(request);
        return new ResponseEntity<>(body, status);
    }

}