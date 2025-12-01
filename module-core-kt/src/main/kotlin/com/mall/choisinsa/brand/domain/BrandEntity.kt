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

    var description: String? = null,

    var webSite: String? = null, // 브랜드 웹 사이트

    var email: String? = null, // 브랜드 이메일

    var address: String? = null, // 영업 소재지 주소

    var managerPhoneNumber: String? = null, // 담당자 휴대폰 번호
) : BaseDateTimeEntity()