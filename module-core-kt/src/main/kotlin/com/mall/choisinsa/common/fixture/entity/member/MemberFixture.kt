package com.mall.choisinsa.common.fixture.entity.member

import com.mall.choisinsa.common.enumeration.MemberStatus
import com.mall.choisinsa.domain.member.Member
import java.time.LocalDateTime

class MemberFixture {
    var id: Long? = 1L
    var loginId: String = "test"
    var password: String = "\$2a\$10\$IM3E8XGnR/suQkfeMk0ce.ppJWAByLoLbPS4Pymy749f1EEo/0JWi" // qwe123
    var status: MemberStatus = MemberStatus.NORMAL
    var name: String = "BRmmbOAZxKY3S2jd8FQKFw=="
    var email: String = "WQcPal8TzpYvJq7t5F7dT/a+cD1kJ/bZpDlenvLYTjE="
    var nickName: String? = "테스터최"
    var phoneNumber: String = "3cLKKmND91F7TMcaS3292Q=="
    var profileFileUrl: String? = "./test_profile_img.png"

    fun build(): Member {
        return Member(
            id = this.id,
            loginId = this.loginId,
            password = this.password,
            status = this.status,
            name = this.name,
            email = this.email,
            nickName = this.nickName,
            phoneNumber = this.phoneNumber,
            profileFileUrl  = this.profileFileUrl,
            createdDt = LocalDateTime.now(),
            updatedDt = null,
        )
    }

    fun build(block: MemberFixture.() -> Unit): Member {
        val builder = MemberFixture()
        builder.apply(block)
        return builder.build()
    }
}