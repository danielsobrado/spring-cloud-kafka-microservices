package com.jds.jvmcc.productservice.config;

import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;
import feign.RequestInterceptor;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.ErrorDecoder;
import feign.okhttp.OkHttpClient;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-13
 */
public class LocalClientConfiguration {

    @Value("${spring.security.user.name}")
    private String username;
  
    @Value("${spring.security.user.password}")
    private String password;

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new ErrorDecoder.Default();
    }

    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }

    @Bean
    @ConditionalOnProperty(name = "spring.security.user.name")
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
      return new BasicAuthRequestInterceptor(this.username, this.password);
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("username", this.username);
            requestTemplate.header("password", this.password);
            requestTemplate.header("Accept", ContentType.APPLICATION_JSON.getMimeType());
        };
    }

}