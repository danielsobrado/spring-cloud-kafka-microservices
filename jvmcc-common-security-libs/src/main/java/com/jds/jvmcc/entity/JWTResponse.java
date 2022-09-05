package com.jds.jvmcc.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-07
 * JWT Authentication Response
 */
@Getter
@RequiredArgsConstructor
public class JWTResponse implements Serializable {
	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
}