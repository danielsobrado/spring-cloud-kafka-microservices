package com.jds.jvmcc.productservice.integration;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.jds.jvmcc.productservice.client.ProductClient;
import com.jds.jvmcc.productservice.entity.Product;

import static com.jds.jvmcc.productservice.integration.ProductMocks.setupMockProductsResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-14
 */
@SpringBootTest
@ActiveProfiles("test")
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { WireMockConfig.class })
public class ProductReviewsAggIntegrationTest {

    @Autowired
    private WireMockServer mockProductsService;

    @Autowired
    private ProductClient productClient;
    
    @BeforeEach
    void setUp() throws IOException {
        setupMockProductsResponse(mockProductsService);
    }
    
    @Test
    public void whenGetProducts_thenProductsShouldBeReturned() {
        var product = productClient.getProduct(ProductConstants.PRODUCT_ID);
        assertNotNull(product);
    }

    @Test
    public void whenGetProducts_thenTheCorrectProductsShouldBeReturned() {
        var product = productClient.getProduct(ProductConstants.PRODUCT_ID);

        assertEquals(ProductConstants.PRODUCT_ID, product.getProductId());
        assertEquals(
                new Product(ProductConstants.PRODUCT_ID, ProductConstants.PRODUCT_MODEL, ProductConstants.PRODUCT_NAME),
                product);
    }
}
