package com.jds.jvmcc.productcrawler.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-08
 */
@Slf4j
@RestController
public class ProductCrawlerService {

    @Value("${product.url"}
    public static final String url = "https://www.adidas.co.uk/api/products/"; 

    @LoadBalanced
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/product/{product_id}")
    public String getProduct(@PathVariable("product_id") String productId, @RequestBody String productId) {
        log.info("Getting product {}", productId);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Product> entity = new HttpEntity<Product>(product,headers);
        
        return restTemplate.exchange(url+productId, HttpMethod.GET, entity, String.class).getBody();
    }
}
