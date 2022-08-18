package com.jds.jvmcc.jvmccapigateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-18
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
            // deepcode ignore DisablesCSRFProtection: <No Frontend Required>
	        .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
			.authorizeRequests()
	        .antMatchers("/login").permitAll()
	        // Any other request, has to be authenticated
	        .anyRequest().authenticated();
		
	}

	/**
	 * Creates a in-memory user management.
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("admin").password("admin").roles("ROLE_ADMIN", "ROLE_USER")
			.and()
			.withUser("jvmcc").password("jvmcc").roles("ROLE_USER");
	}
	
}