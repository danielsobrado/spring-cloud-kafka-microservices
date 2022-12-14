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
 * @version 1.1
 * @since 2022-08-05
 */
@Schema(description = "Product")
@EqualsAndHashCode(callSuper=false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Product {

    @Schema(example = "M20324", required = true)
    @JsonProperty("id")
    private String productId;

    @Schema(example = "ION05", required = true)
    @JsonProperty("model_number")
    private String model;

    @Schema(example = "Stan Smith Shoes", required = true)
    @JsonProperty("name")
    private String name;

}
