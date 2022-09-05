package com.jds.jvmcc.jvmccapigateway.config;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
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

