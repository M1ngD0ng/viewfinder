package com.sparta.viewfinder.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Timestamped {

    @CreatedDate
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private String createdAt;

    @LastModifiedDate
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private String modifiedAt;

    public void onPrePersist() {
        createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/mm/dd HH:mm:ss"));
        this.modifiedAt = this.createdAt;
    }

    @PrePersist
    public void onPreUpdate() {
        modifiedAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/mm/dd HH:mm:ss"));
    }


}