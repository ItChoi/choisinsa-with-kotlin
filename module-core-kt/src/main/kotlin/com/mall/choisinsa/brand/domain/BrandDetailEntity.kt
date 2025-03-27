package com.mall.choisinsa.brand.domain

import com.mall.choisinsa.common.domain.BaseDateTimeEntity
import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "brand_detail")
class BrandDetailEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var brandId: Long? = null,

    var logoFileUrl: String? = null,

    var logoFilename: String? = null,

    var description: String? = null,

    var webSite: String? = null,

    var email: String? = null,

    var address: String? = null,

    var managerPhoneNumber: String? = null,

) : BaseDateTimeEntity() {
}