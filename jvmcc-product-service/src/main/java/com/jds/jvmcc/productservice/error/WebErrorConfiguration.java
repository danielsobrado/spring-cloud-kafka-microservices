package com.jds.jvmcc.productservice.error;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jds.jvmcc.productservice.error.attributes.ProductAppErrorAttributes;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-21
 */
@Configuration
public class WebErrorConfiguration {

    @Value("${product.service.api.version}")
    private String currentApiVersion;

    /**
     * We override the default {@link DefaultErrorAttributes}
     *
     * @return A custom implementation of ErrorAttributes
     */
    @Bean
    public ErrorAttributes errorAttributes() {
        return new ProductAppErrorAttributes(currentApiVersion);
    }

}