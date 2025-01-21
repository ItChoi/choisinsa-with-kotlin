package com.mall.choisinsa.repository.member

import com.mall.choisinsa.common.fixture.entity.member.MemberFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest


@DataJpaTest(showSql = true)
class CoreMemberRepositoryTest @Autowired constructor(
    private val coreMemberRepository: CoreMemberRepository,
) {

    @Test
    fun H2_DB_정상_연결() {
        // given
        val member = MemberFixture().build { id = null }

        // when
        coreMemberRepository.save(member)

        // then
        assertThat(member.id).isNotNull()
    }
}
