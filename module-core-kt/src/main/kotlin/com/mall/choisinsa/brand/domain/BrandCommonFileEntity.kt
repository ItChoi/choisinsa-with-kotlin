package com.mall.choisinsa.brand.domain

import com.mall.choisinsa.common.domain.CreateDateTimeEntity
import com.mall.choisinsa.common.enumeration.BrandCommonFileType
import com.mall.choisinsa.common.enumeration.CommonStatus
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "brand_common_file")
class BrandCommonFileEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val brandId: Long,
    val commonFileId: Long,
    val type: BrandCommonFileType,
    val status: CommonStatus,
) : CreateDateTimeEntity()