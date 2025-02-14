package com.mall.choisinsa.terms.service

import com.mall.choisinsa.terms.domain.dto.MemberTermsRequest
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CoreMemberTermsServiceTest {


    @Test
    @DisplayName("")
    fun test() {
        val list = listOf(
            MemberTermsRequest(1, true),
            MemberTermsRequest(2, true),
            MemberTermsRequest(3, false),
            MemberTermsRequest(4, true),
        )

        val ids: List<Long> = list.map { it.memberTermsId }
        assertThat(ids).containsAnyElementsOf(mutableListOf(1, 2, 3, 4))
    }

    @Test
    @DisplayName("")
    fun test123() {
        val associateBy = listOf(
            MemberTermsRequest(1, true),
            MemberTermsRequest(2, true),
            MemberTermsRequest(3, false),
            MemberTermsRequest(4, true),
        ).associateBy { it.memberTermsId }

        println(associateBy)
    }

    @Test
    @DisplayName("")
    fun test456() {
        val list1 = listOf(1, 2, 3)
        val list2 = listOf(2, 3)

        if (list1.containsAll(list2)) {
            println("true")
        } else {
            println("false")
        }
    }

}