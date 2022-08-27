package com.jds.jvmcc.reviewservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.jds.jvmcc.config.WhiteListConfiguration;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-26
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

	private static final String REVIEW_URL = "/review/**";
	private static final String REVIEW_DELETE_URL = "/review/delete/**";
	private static final String USER_ROLE = "USER";
	private static final String ADMIN_ROLE = "ADMIN";

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Disable CSRF protection.
		// deepcode ignore DisablesCSRFProtection: <No Frontend Required>
		http.csrf().disable();
		// Allow access to the following endpoints without authentication.
		http.authorizeRequests().antMatchers(REVIEW_URL).permitAll();
		// Allow whitlisted requests without authentication.
		http.authorizeRequests().antMatchers(WhiteListConfiguration.ACTUATOR_WHITELIST).permitAll();
		http.authorizeRequests().antMatchers(WhiteListConfiguration.SWAGGER_WHITELIST).permitAll();
		// Any POST, PUT, DELETE, PATCH requests must be authenticated with the required
		// role.
		http.authorizeRequests().antMatchers(HttpMethod.POST, REVIEW_URL).hasRole(USER_ROLE);
		http.authorizeRequests().antMatchers(HttpMethod.PUT, REVIEW_URL).hasRole(ADMIN_ROLE);
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, REVIEW_DELETE_URL).hasRole(ADMIN_ROLE);
		// All other requests must be authenticated.
		http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
	}

}