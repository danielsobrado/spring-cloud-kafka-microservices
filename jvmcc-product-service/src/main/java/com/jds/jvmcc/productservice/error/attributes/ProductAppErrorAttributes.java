package com.jds.jvmcc.productservice.error.attributes;

import java.util.Map;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.WebRequest;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-21
 */
public class ProductAppErrorAttributes extends DefaultErrorAttributes {
    private final String currentApiVersion;

    public ProductAppErrorAttributes(final String currentApiVersion) {
        this.currentApiVersion = currentApiVersion;
    }

    @Override
    public Map<String, Object> getErrorAttributes(final WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
        errorAttributes.put("apiVersion", currentApiVersion);
        return errorAttributes;
    }
}
