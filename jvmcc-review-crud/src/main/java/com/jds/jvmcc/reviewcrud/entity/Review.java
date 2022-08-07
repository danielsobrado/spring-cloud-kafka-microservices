package com.jds.jvmcc.reviewcrud.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
 * @since 2022-08-05
 */
@Schema(description = "Review")
@Entity(name = "review")
@Table(name = "review", uniqueConstraints = {
        @UniqueConstraint(name = "product_id_unique", columnNames = "product_id"),
})
@SequenceGenerator(initialValue = 1, name = "idgen", sequenceName = "review_seq")
@EqualsAndHashCode(callSuper=false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Review extends BaseEntity {

    @Column(name = "product_id", nullable = false)
    @Schema(example = "M20324", required = true)
    private String productId;

    @Column(name = "average_review_score", nullable = false)
    @Schema(example = "1.1", required = true)
    private Float averageReviewScore;

    @Column(name = "number_reviews", nullable = false)
    @Schema(example = "12", required = true)
    private Integer numberReviews;

}
