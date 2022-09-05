package com.jds.jvmcc.productservice.integration;

import static com.jds.jvmcc.productservice.integration.ProductConstants.PRODUCT_ID;
import static com.jds.jvmcc.productservice.integration.ProductConstants.PRODUCT_MODEL;
import static com.jds.jvmcc.productservice.integration.ProductConstants.PRODUCT_NAME;
import static com.jds.jvmcc.productservice.integration.ProductMocks.setupMockProductsResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-14
 */
@Disabled
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
    public void whenGetProduct_thenProductShouldBeReturned() {
        var product = productClient.getProduct(PRODUCT_ID);
        assertNotNull(product);
    }

    @Test
    public void whenGetProduct_thenTheCorrectProductShouldBeReturned() {
        var product = productClient.getProduct(ProductConstants.PRODUCT_ID);

        assertEquals(PRODUCT_ID, product.getProductId());
        assertEquals(new Product(PRODUCT_ID, PRODUCT_MODEL, PRODUCT_NAME), product);
    }
}
