package com.mall.choisinsa.common.domain

import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
class BaseDateTimeEntity {
    @CreatedDate
    var createdDt: LocalDateTime? = null

    @LastModifiedDate
    var updatedDt: LocalDateTime? = null
}