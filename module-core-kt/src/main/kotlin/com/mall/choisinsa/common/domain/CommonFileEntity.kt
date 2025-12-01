package com.mall.choisinsa.common.domain

import com.mall.choisinsa.common.enumeration.CommonFileType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "common_file")
@Entity
class CommonFileEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,

    val type: CommonFileType,

    val filePath: String,

    val filename: String,

    val originalFilename: String

) : CreateDateTimeEntity()