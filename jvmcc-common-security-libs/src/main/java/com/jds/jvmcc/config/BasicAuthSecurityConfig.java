package com.jds.jvmcc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jds.jvmcc.entity.Role;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-06
 */
@Profile(Profiles.BASIC_AUTH)
@Configuration
@EnableConfigurationProperties
@EnableWebSecurity
public class BasicAuthSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${security.basicAuth.username}")
	private String basicAuthUsername;

	@Value("${security.basicAuth.password}")
	private String basicAuthPassword;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Disable CSRF
		// deepcode ignore DisablesCSRFProtection: <No Frontend Required>
        http.csrf().disable()
				// Only admin can perform HTTP delete and update reviews
				.authorizeRequests()
				.antMatchers(HttpMethod.GET).permitAll()
				.antMatchers(HttpMethod.DELETE).hasRole(Role.ROLE_ADMIN)
				.antMatchers(HttpMethod.PUT, "/review/**").hasAnyRole(Role.ADMIN)
				.antMatchers(HttpMethod.POST, "/review/**").hasAnyRole(Role.ADMIN, Role.ROLE_USER)
				.and().httpBasic()
				.and().authorizeRequests().anyRequest().permitAll()
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	public UserDetailsService userDetailsService() {
		return userDetailsService;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth.inMemoryAuthentication().passwordEncoder(encoder).withUser(this.basicAuthUsername)
				.password(encoder.encode(this.basicAuthPassword)).roles("USER");
	}

	// @Override
	// protected void configure(HttpSecurity http) throws Exception {
	// 	// deepcode ignore DisablesCSRFProtection: <No Frontend Required>
	// 	http.authorizeRequests().anyRequest().authenticated().and().httpBasic().and().csrf().disable();
	// }
}