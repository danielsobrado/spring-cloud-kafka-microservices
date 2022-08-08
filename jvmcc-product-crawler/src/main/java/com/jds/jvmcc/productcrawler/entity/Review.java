package com.jds.jvmcc.productcrawler.entity;

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
    private String productId;

    @Column(name = "review_score", nullable = false)
    @Schema(example = "1", required = true)
    private Integer reviewScore;

    @Column(name = "review_comment", nullable = false)
    @Schema(example = "1", required = true)
    private String reviewComment;

}
