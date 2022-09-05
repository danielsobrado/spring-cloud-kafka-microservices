package com.jds.jvmcc.productservice.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author J. Daniel Sobrado
 * @version 1.1
 * @since 2022-08-06
 */
@Data @NoArgsConstructor @MappedSuperclass
@JsonIdentityInfo(
  generator = ObjectIdGenerators.PropertyGenerator.class, 
  property = "id")
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgen")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column(
        name = "create_date",
        nullable = true,
        updatable = false
    )
    @CreatedDate
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Date createDate;

    @Column(
        name = "update_date",
        nullable = true
    )
    @LastModifiedDate
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Date updateDate;

    @Column(
        name = "last_update_user",
        nullable = true
    )
    private String lastUpdateUser;

    @JsonCreator
    public BaseEntity(long id) {
        this.setId(id);
    }

    @Override
    public int hashCode() {
        return (getId() != null)
        	? Objects.hash(getId())
        	: super.hashCode();
    }
    
    @Override
    public boolean equals(Object other) {
        return (getId() != null && getClass().isInstance(other) && other.getClass().isInstance(this))
            ? getId().equals(((BaseEntity) other).getId())
            : (other == this);
    }

}
