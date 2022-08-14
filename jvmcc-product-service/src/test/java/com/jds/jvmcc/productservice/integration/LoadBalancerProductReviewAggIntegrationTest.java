package com.jds.jvmcc.productservice.integration;

import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.moreThan;
import static com.jds.jvmcc.productservice.integration.ProductMocks.setupMockProductsResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
import com.github.tomakehurst.wiremock.client.WireMock;
import com.jds.jvmcc.productservice.JvmccProductServiceApplication;
import com.jds.jvmcc.productservice.client.ProductClient;
import com.jds.jvmcc.productservice.entity.Product;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-14
 */
@SpringBootTest(classes = JvmccProductServiceApplication.class)
@ActiveProfiles("ribbon-test")
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { RibbonTestConfig.class })
public class LoadBalancerProductReviewAggIntegrationTest {
    @Autowired
    private WireMockServer mockProductsService;

    @Autowired
    private WireMockServer secondMockProductsService;

    @Autowired
    private ProductClient productClient;

    @BeforeEach
    void setUp() throws IOException {
        setupMockProductsResponse(mockProductsService);
        setupMockProductsResponse(secondMockProductsService);
    }

    @Test
    void whenGetProduct_thenRequestsAreLoadBalanced() {
        for (int k = 0; k < 10; k++) {
            productClient.getProduct(ProductConstants.PRODUCT_ID);
        }

        mockProductsService.verify(
          moreThan(0), getRequestedFor(WireMock.urlEqualTo("/product")));
        secondMockProductsService.verify(
          moreThan(0), getRequestedFor(WireMock.urlEqualTo("/product")));
    }

    @Test
    public void whenGetProduct_thenTheCorrectProductShouldBeReturned() {
        var product = productClient.getProduct(ProductConstants.PRODUCT_ID);

        assertEquals(ProductConstants.PRODUCT_ID, product.getProductId());
        assertEquals(
                new Product(ProductConstants.PRODUCT_ID, ProductConstants.PRODUCT_MODEL, ProductConstants.PRODUCT_NAME),
                product);
    }
}
