package com.jds.jvmcc.jvmccapigateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-19
 */
@Configuration
public class UserDetailServiceConfig {

    @Value("${security.basicAuth.user.username}")
	private String userAuthUsername;

	@Value("${security.basicAuth.user.password}")
	private String userAuthPassword;

    @Value("${security.basicAuth.admin.username}")
	private String adminAuthUsername;

	@Value("${security.basicAuth.admin.password}")
	private String adminAuthPassword;


    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";

    @Bean
    public UserDetailsService userDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername(userAuthUsername)
          .password(bCryptPasswordEncoder.encode(userAuthUsername))
          .roles(ROLE_USER)
          .build());
        manager.createUser(User.withUsername(adminAuthUsername)
          .password(bCryptPasswordEncoder.encode(adminAuthUsername))
          .roles(ROLE_ADMIN, ROLE_USER)
          .build());
        return manager;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
