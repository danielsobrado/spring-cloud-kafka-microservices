// package com.jds.jvmcc.productservice.integration;

// import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
// import static com.github.tomakehurst.wiremock.client.WireMock.moreThan;
// import static com.jds.jvmcc.productservice.integration.ProductConstants.PRODUCT_ID;
// import static com.jds.jvmcc.productservice.integration.ProductConstants.PRODUCT_MODEL;
// import static com.jds.jvmcc.productservice.integration.ProductConstants.PRODUCT_NAME;
// import static com.jds.jvmcc.productservice.integration.ProductMocks.setupMockProductsResponse;
// import static org.junit.jupiter.api.Assertions.assertEquals;

// import java.io.IOException;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Disabled;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.context.properties.EnableConfigurationProperties;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.ActiveProfiles;
// import org.springframework.test.context.ContextConfiguration;
// import org.springframework.test.context.junit.jupiter.SpringExtension;

// import com.github.tomakehurst.wiremock.WireMockServer;
// import com.github.tomakehurst.wiremock.client.WireMock;
// import com.jds.jvmcc.productservice.JvmccProductServiceApplication;
// import com.jds.jvmcc.productservice.client.ProductClient;
// import com.jds.jvmcc.productservice.entity.Product;

// /**
//  * @author J. Daniel Sobrado
//  * @version 1.0
//  * @since 2022-08-14
//  */
// @Disabled
// @SpringBootTest(classes = JvmccProductServiceApplication.class)
// @ActiveProfiles("ribbon-test")
// @EnableConfigurationProperties
// @ExtendWith(SpringExtension.class)
// @ContextConfiguration(classes = { RibbonTestConfig.class })
// public class LoadBalancerProductReviewAggIntegrationTest {

//     @Autowired
//     private WireMockServer mockProductService;

//     @Autowired
//     private WireMockServer secondMockProductService;

//     @Autowired
//     private ProductClient productClient;

//     @BeforeEach
//     void setUp() throws IOException {
//         setupMockProductsResponse(mockProductService);
//         setupMockProductsResponse(secondMockProductService);
//     }

//     @Test
//     void whenGetProduct_thenRequestsAreLoadBalanced() {
//         for (int k = 0; k < 10; k++) {
//             productClient.getProduct(PRODUCT_ID);
//         }

//         mockProductService.verify(
//           moreThan(0), getRequestedFor(WireMock.urlEqualTo("/product")));
//         secondMockProductService.verify(
//           moreThan(0), getRequestedFor(WireMock.urlEqualTo("/product")));
//     }

//     @Test
//     public void whenGetProduct_thenTheCorrectProductShouldBeReturned() {
//         var product = productClient.getProduct(PRODUCT_ID);

//         assertEquals(PRODUCT_ID, product.getProductId());
//         assertEquals(new Product(PRODUCT_ID, PRODUCT_MODEL, PRODUCT_NAME), product);
//     }

// }
