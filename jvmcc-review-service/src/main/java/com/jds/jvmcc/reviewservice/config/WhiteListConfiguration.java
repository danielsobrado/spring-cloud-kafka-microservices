package com.jds.jvmcc.reviewservice.config;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-09
 */
public class WhiteListConfiguration {
    private WhiteListConfiguration() {
	}

    protected static final String[] ACTUATOR_WHITELIST = {
        "/actuator/**"
    };

    protected static final String[] SWAGGER_WHITELIST = {
        "/v3/api-docs/**",
        "/swagger-ui/**",
        "/swagger-ui.html",
    };
}   

