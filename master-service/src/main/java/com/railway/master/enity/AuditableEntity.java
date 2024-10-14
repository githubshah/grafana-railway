package com.railway.master.enity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditableEntity<U> {

    @CreatedBy
    protected U createdBy;

    @CreatedDate
    protected LocalDateTime createdOn;

    @LastModifiedBy
    protected U modifiedBy;

    @LastModifiedDate
    protected LocalDateTime modifiedOn;
}
