package com.jds.jvmcc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.jds.jvmcc.error.ErrorCode;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-06
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException implements ErrorCode {
	
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException() {
		super();
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
	}

	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	@Override
    public String getErrorCode() {
        return "NF-001";
    }
}