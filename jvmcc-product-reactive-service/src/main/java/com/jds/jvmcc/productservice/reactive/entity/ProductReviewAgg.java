package com.jds.jvmcc.productservice.reactive.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-13
 */
@Schema(description = "ProductReviewAGG")
@EqualsAndHashCode(callSuper=false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ProductReviewAgg {

    @Schema(example = "M20324", required = true)
    private String productId;

    @Schema(example = "1.1", required = true)
    private Float averageReviewScore;

    @Schema(example = "12", required = true)
    private Integer numberReviews;

}
