package com.jds.jvmcc.config;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-09
 */
public class WhiteListConfiguration {
    private WhiteListConfiguration() {
	}

    public static final String[] ACTUATOR_WHITELIST = {
        "/actuator/**"
    };

    public static final String[] SWAGGER_WHITELIST = {
        "/v3/api-docs/**",
        "/swagger-ui/**",
        "/swagger-ui.html",
    };
}   

