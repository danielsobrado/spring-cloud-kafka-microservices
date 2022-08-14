package com.jds.jvmcc.productservice.config;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.cache.CacheBuilder;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-14
 */
@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {
	@Bean
	@Override
	public CacheManager cacheManager() {
		ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager() {

			@Override
			protected Cache createConcurrentMapCache(final String name) {
				return new ConcurrentMapCache(name, CacheBuilder.newBuilder().expireAfterWrite(10, TimeUnit.SECONDS)
						.maximumSize(100).build().asMap(), false);
			}
		};

		cacheManager.setCacheNames(Arrays.asList("productCache"));
		return cacheManager;
	}

}