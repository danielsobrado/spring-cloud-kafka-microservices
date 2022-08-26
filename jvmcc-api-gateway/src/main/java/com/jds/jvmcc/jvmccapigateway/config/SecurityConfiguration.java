package com.jds.jvmcc.jvmccapigateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.oidc.web.server.logout.OidcClientInitiatedServerLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-18
 */
@Configuration
public class SecurityConfiguration {

  public static final String ROLE_ADMIN = "ADMIN";
  public static final String ROLE_USER = "USER";

  @Value("${spring.security.debug:false}")
  boolean securityDebug;

  @Bean
  public SecurityWebFilterChain filterChain(ServerHttpSecurity http, ServerLogoutSuccessHandler handler) {
    // Disable CSRF
    // deepcode ignore DisablesCSRFProtection: <No Frontend Required>
    http.csrf().disable()
        // Only admin can perform HTTP delete and update reviews
        .authorizeExchange()
        .pathMatchers("/actuator/**", "/","/logout.html").permitAll()
        .pathMatchers(HttpMethod.GET).permitAll()
        .pathMatchers(HttpMethod.DELETE).hasRole(ROLE_ADMIN)
        .pathMatchers(HttpMethod.PUT, "/review/**").hasAnyRole(ROLE_ADMIN)
        .pathMatchers(HttpMethod.POST, "/review/**").hasAnyRole(ROLE_ADMIN, ROLE_USER)
        .and().httpBasic()
        .and().authorizeExchange().anyExchange()
        .authenticated()
        .and()
        .oauth2Login() // to redirect to oauth2 login page.
        .and()
        .logout()
        .logoutSuccessHandler(handler);

    return http.build();

  }

  @Bean
	public ServerLogoutSuccessHandler keycloakLogoutSuccessHandler(ReactiveClientRegistrationRepository repository) {

        OidcClientInitiatedServerLogoutSuccessHandler oidcLogoutSuccessHandler =
                new OidcClientInitiatedServerLogoutSuccessHandler(repository);

        oidcLogoutSuccessHandler.setPostLogoutRedirectUri("{baseUrl}/logout.html");

        return oidcLogoutSuccessHandler;
    }

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) -> web.debug(securityDebug)
        .ignoring()
        .antMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/favicon.ico");
  }

}