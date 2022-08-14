package com.jds.jvmcc.productservice.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.jds.jvmcc.productservice.entity.Product;
import com.jds.jvmcc.productservice.entity.ProductReviewAgg;
import com.jds.jvmcc.productservice.entity.Review;
import com.jds.jvmcc.productservice.service.impl.ProductReviewAggServiceImpl;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-14
 * 
 * Unit test for {@link ProductReviewAggServiceImpl}
 */
@ExtendWith(MockitoExtension.class)
public class ProductReviewAggTests {

    private static final String PRODUCT_ID = "M20324";
    private static final String PRODUCT_NAME = "Stan Smith Shoes";
    private static final String PRODUCT_MODEL = "ION05";

    @Mock
    private ProductConsumerService productConsumerService;

    @Mock
    private ReviewConsumerService reviewConsumerService;

    @InjectMocks
    private ProductReviewAggService service = new ProductReviewAggServiceImpl();

    @Before
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

    @Test
    public void testGetProductSummary() {

        var product = new Product(PRODUCT_ID, PRODUCT_MODEL, PRODUCT_NAME);
        
        when(productConsumerService.getProduct(PRODUCT_ID)).thenReturn(product);  

        var review1 = new Review(PRODUCT_ID, 2, "Broke fast..");
        var review2 = new Review(PRODUCT_ID, 3, "OK shoes");
        var review3 = new Review(PRODUCT_ID, 4, "Good shoes");
        var review4 = new Review(PRODUCT_ID, 5, "Great shoes!");
        var review5 = new Review(PRODUCT_ID, 3, "Decent shoes");
        var review6 = new Review(PRODUCT_ID, 1, "Not so good shoes");

        var reviews = List.of(review1, review2, review3, review4, review5, review6);

        when(reviewConsumerService.getReviewsByProduct(PRODUCT_ID)).thenReturn(reviews);

        Optional<ProductReviewAgg> productReviewAgg = service.getProductSummary(PRODUCT_ID);

        // Check if it is present
        assertEquals(true, productReviewAgg.isPresent());

        // Retrieve the product
        var productReviewAggProduct = productReviewAgg.get();

        // Check if the product is correct
        assertEquals(product.getProductId(), productReviewAggProduct.getProductId());
        assertEquals(product.getName(), productReviewAggProduct.getName());
        assertEquals(PRODUCT_MODEL, productReviewAggProduct.getModel());

        // Check if the reviews are correct
        assertEquals(reviews.size(), (int)productReviewAggProduct.getReviewCount());
        assertEquals(3.0d, productReviewAggProduct.getAverageReviewScore(), 0.1d);

    }
    
}
