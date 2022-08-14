package com.jds.jvmcc.jvmccconfigurationserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-11
 */
@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf()
          .ignoringAntMatchers("/encrypt/**")
          .ignoringAntMatchers("/decrypt/**");

        super.configure(http);
  }
}
