package com.jds.jvmcc.productservice.config;

import org.springframework.context.annotation.Bean;

import feign.Logger;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-13
 */
public class NoFeignConfig {

    @Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}
    
}
