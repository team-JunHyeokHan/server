package com.proj.bamulguan.global.commoon.entity

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.time.LocalTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BasTimeEntity {

    @CreatedDate
    @Column(updatable = false)
    val createdAt: LocalDateTime? = null

    @LastModifiedDate
    val modifiedAt: LocalDateTime? = null

}
