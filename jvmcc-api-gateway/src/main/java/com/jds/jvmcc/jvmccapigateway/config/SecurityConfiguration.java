package com.jds.jvmcc.jvmccapigateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-18
 */
@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

  public static final String ROLE_ADMIN = "ADMIN";
  public static final String ROLE_USER = "USER";

  @Value("${spring.security.debug:false}")
  boolean securityDebug;

  @Bean
  public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
      // Disable CSRF
      // deepcode ignore DisablesCSRFProtection: <No Frontend Required>
      http.csrf().disable()
        .authorizeExchange()
        .pathMatchers(HttpMethod.POST, "/token-service/**").permitAll()
        .pathMatchers(WhiteListConfiguration.ACTUATOR_WHITELIST).permitAll()
        .pathMatchers(WhiteListConfiguration.SWAGGER_WHITELIST).permitAll()
        .pathMatchers(HttpMethod.GET, "/product/**").permitAll()
        .pathMatchers(HttpMethod.GET, "/review/**").permitAll()
        .pathMatchers(HttpMethod.POST, "/review/save/**").permitAll()
        .pathMatchers(HttpMethod.PUT, "/review/update/**").permitAll()
        .pathMatchers(HttpMethod.DELETE, "/review/delete/**").permitAll()
        .and().oauth2ResourceServer().jwt();
      return http.build();
  }

}