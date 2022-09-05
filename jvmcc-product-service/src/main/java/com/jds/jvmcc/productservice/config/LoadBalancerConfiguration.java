package com.jds.jvmcc.productservice.config;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-30
 */
@Slf4j
@Configuration
public class LoadBalancerConfiguration {
    @Bean
    public ServiceInstanceListSupplier
 discoveryClientServiceInstanceListSupplier(
          ConfigurableApplicationContext context) {
       log.info("Configuring Load balancer to prefer the same instance");
       return ServiceInstanceListSupplier.builder()
                .withBlockingDiscoveryClient()
                .withSameInstancePreference()
                .build(context);
       }
 }
