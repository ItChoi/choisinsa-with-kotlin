package com.mall.choisinsa.brand.domain

import com.mall.choisinsa.common.domain.BaseDateTimeEntity
import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "company")
class CompanyEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var countryName: String? = null,

    var countryCode: String? = null,

    var ceoName: String? = null,

    var name: String? = null,

    var contactNumber: String? = null,

    var bizNumber: String? = null,

    var address: String? = null,

    var eCommerceRegistration: String? = null,

) : BaseDateTimeEntity() {

}