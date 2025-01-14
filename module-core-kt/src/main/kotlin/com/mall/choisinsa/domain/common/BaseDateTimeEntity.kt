package com.mall.choisinsa.domain.common

import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
class BaseDateTimeEntity(
    @CreatedDate
    private val createdDt: LocalDateTime? = null,

    @CreatedDate
    private val updatedDt: LocalDateTime? = null,
) {
}