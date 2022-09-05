package com.jds.jvmcc.jvmccapigateway.web;

import reactor.core.publisher.Mono;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-16
 */
@Configuration
public class WebEndpoints {

	@Bean
	public RouterFunction<ServerResponse> routerFunction() {
		return RouterFunctions.route()
				.GET("/review-fallback", request ->
						ServerResponse.ok().body(Mono.just(""), String.class))
				.POST("/review-fallback", request ->
						ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE).build())
                .PUT("/review-fallback", request ->
                        ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE).build())
                .DELETE("/review-fallback", request ->
                        ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE).build())
                .GET("/product-fallback", request ->
                        ServerResponse.ok().body(Mono.just(""), String.class))
				.build();
	}
	
}