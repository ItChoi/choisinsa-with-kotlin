package com.mall.choisinsa.item.domain

import com.mall.choisinsa.common.domain.CreateDateTimeEntity
import com.mall.choisinsa.common.enumeration.CommonStatus
import com.mall.choisinsa.common.enumeration.ItemCommonFileType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "item_common_file")
class ItemCommonFileEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var itemId: Long,

    var commonFileId: Long,

    var type: ItemCommonFileType,

    var status: CommonStatus,

    ) : CreateDateTimeEntity()