package com.jds.jvmcc.productservice.integration;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jds.jvmcc.productservice.JvmccProductServiceApplication;
import com.jds.jvmcc.productservice.client.ProductClient;
import com.jds.jvmcc.productservice.entity.Product;
import com.netflix.discovery.EurekaClient;

import static com.jds.jvmcc.productservice.integration.ProductConstants.*;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-14
 */
@Disabled
@ActiveProfiles("eureka-test")
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = JvmccProductServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = { MockProductServiceConfig.class }, initializers = {
        EurekaContainerConfig.Initializer.class })
public class ServiceDiscoveryProductsClientIntegrationTest {

    @Autowired
    private ProductClient productClient;

    @Lazy
    @Autowired
    private EurekaClient eurekaClient;

    @BeforeEach
    void setUp() {
        await().atMost(60, SECONDS).until(() -> eurekaClient.getApplications().size() > 0);
    }

    @Test
    public void whenGetProduct_thenTheCorrectProdutuctAreReturned() {
        var product = productClient.getProduct(PRODUCT_ID);

        assertEquals(PRODUCT_ID, product.getProductId());
        assertEquals(new Product(PRODUCT_ID, PRODUCT_MODEL, PRODUCT_NAME), product);
    }
}
