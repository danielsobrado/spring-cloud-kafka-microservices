package com.jds.jvmcc.reviewservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.annotations.Type;

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
 * @since 2022-08-05
 */
@Schema(description = "Review")
@Entity(name = "review")
@Table(name = "review")
@SequenceGenerator(initialValue = 1, name = "idgen", sequenceName = "review_seq", @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY ))
@EqualsAndHashCode(callSuper=false)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Review extends BaseEntity {

    @Column(name = "product_id", nullable = false)
    @Schema(example = "M20324", required = true)
    private String productId;

    @Column(name = "review_score", nullable = false, columnDefinition = "SMALLINT")
    @Type(type = "org.hibernate.type.ShortType")
    @Schema(example = "1", required = true)
    // Validate that the number that is 1 to 5
    @Min(1) @Max(5)
    private Short reviewScore;

    @Column(name = "comment", nullable = false)
    @Schema(example = "This is my review", required = false)
    private String comment;

}
