// package com.jds.jvmcc.reviewservice.reactive.config;

// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.WebSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// /**
//  * @author J. Daniel Sobrado
//  * @version 1.0
//  * @since 2022-08-13
//  */
// @EnableWebSecurity
// @Configuration
// class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//     @Override
//     public void configure(WebSecurity web) throws Exception {
//         http.authorizeRequests()
//             .antMatchers("/employee/me").authenticated()
//             .antMatchers("/**").permitAll();
//     }
// }
