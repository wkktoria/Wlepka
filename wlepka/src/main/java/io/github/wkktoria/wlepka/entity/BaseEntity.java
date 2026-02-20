package io.github.wkktoria.wlepka.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "created_by", nullable = false, updatable = false, length = 20)
    @CreatedBy
    private String createdBy;

    @Column(name = "updated_at", insertable = false)
    @LastModifiedDate
    @UpdateTimestamp
    private Instant updatedAt;

    @Column(name = "updated_by", insertable = false, length = 20)
    @LastModifiedBy
    private String updatedBy;

}
