package com.jds.jvmcc.productservice.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-08
 */
@Schema(description = "ProductReviewAGG")
@EqualsAndHashCode(callSuper=false)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductReviewAgg {

    @Schema(example = "M20324", required = true)
    private String productId;

    @Schema(example = "Stan Smith Shoes", required = true)
    private String name;

    @Schema(example = "ION05", required = true)
    private String model;

    @Schema(example = "1.1", required = true)
    private Double averageReviewScore;

    @Schema(example = "12", required = true)
    private Integer reviewCount;

    // Create constructor based on product
    public ProductReviewAgg(Product product, Double averageReviewScore, Integer reviewCount) {
        this.productId = product.getProductId();
        this.name = product.getName();
        this.model = product.getModel();
        this.averageReviewScore = averageReviewScore;
        this.reviewCount = reviewCount;
    }

}
