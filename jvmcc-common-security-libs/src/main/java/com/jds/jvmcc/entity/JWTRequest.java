package com.jds.jvmcc.entity;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-07
 * JWT Authentication Request
 */
@Data
@NoArgsConstructor
public class JWTRequest implements Serializable {
	private static final long serialVersionUID = 5926468583005150707L;
	@Schema(example = "user")
	private String username;
	@Schema(example = "password")
	private String password;
}