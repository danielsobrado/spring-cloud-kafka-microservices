package com.jds.jvmcc.reviewservice.reactive.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jds.jvmcc.reviewservice.reactive.repository.ReviewRepository;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-13
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@EnableWebMvc
public class ReviewControllerTests {

    static final String PRODUCT_ID = "12345";

    @Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ReviewController reviewController;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup(){
        reviewRepository.deleteAllByProductId(PRODUCT_ID);
    }

    @Test
    void test() {
        // TODO
    }
    

}
