package com.jds.jvmcc.productservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-08
 */
@Schema(description = "Product")
@EqualsAndHashCode(callSuper=false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Review {

    @Schema(example = "M20324", required = true)
    @JsonProperty("product_id")
    private String productId;

    @Schema(example = "1", required = true)
    @JsonProperty("review_score")
    private Integer rating;

    @Schema(example = "1", required = true)
    @JsonProperty("comment")
    private String comment;

}
