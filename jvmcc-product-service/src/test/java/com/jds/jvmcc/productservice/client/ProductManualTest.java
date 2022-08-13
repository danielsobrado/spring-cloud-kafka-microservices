package com.jds.jvmcc.productservice.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jds.jvmcc.productservice.JvmccProductServiceApplication;
import com.jds.jvmcc.productservice.service.ProductConsumerService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JvmccProductServiceApplication.class)
public class ProductManualTest {

    private static final String PRODUCT_ID = "M20324";

    @Autowired
    private ProductConsumerService productConsumerService;

    @Test
    public void whenSpringContextIsBootstrapped_thenNoExceptions() {
    }

    @Test
    public void whenGetPosts_thenListPostSizeGreaterThanZero() {

        var product = productConsumerService.getProduct(PRODUCT_ID);

        assertNotNull(product);
        
    }

    // @Test
    // public void whenGetPostWithId_thenPostExist() {

    //     Post post = productService.getPostById(1L);

    //     assertNotNull(post);
    // }

}