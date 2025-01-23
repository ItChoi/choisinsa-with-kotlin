package com.mall.choisinsa.common.domain

import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
class BaseDateTimeEntity(
    @CreatedDate
    val createdDt: LocalDateTime? = null,

    @CreatedDate
    val updatedDt: LocalDateTime? = null,
) {
}