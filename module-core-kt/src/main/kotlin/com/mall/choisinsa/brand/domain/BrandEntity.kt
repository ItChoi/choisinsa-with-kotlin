package com.mall.choisinsa.brand.domain

import com.mall.choisinsa.common.domain.BaseDateTimeEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "brand")
class BrandEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var companyId: Long,

    var nameEn: String? = null,

    var nameKo: String? = null,

) : BaseDateTimeEntity() {

}