package com.jds.jvmcc.reviewcrud.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-05
 */
@Entity(name = "review")
@Table(name = "review", uniqueConstraints = {
        @UniqueConstraint(name = "product_id_unique", columnNames = "product_id"),
})
@SequenceGenerator(initialValue = 1, name = "idgen", sequenceName = "review_seq")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Review extends BaseEntity {

    @Column(name = "product_id", nullable = false)
    private String productId;

    @Column(name = "average_review_score", nullable = false)
    private Float averageReviewScore;

    @Column(name = "number_reviews", nullable = false)
    private Integer numberReviews;

}
