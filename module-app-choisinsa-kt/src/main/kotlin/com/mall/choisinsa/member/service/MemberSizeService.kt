package com.mall.choisinsa.member.service

import com.mall.choisinsa.member.domain.dto.response.MemberSizeResponse
import com.mall.choisinsa.member.service.port.MemberSizeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberSizeService(
    private val memberSizeRepository: MemberSizeRepository,
) {
    @Transactional(readOnly = true)
    fun findAllMemberSizeResponseBy(memberId: Long): List<MemberSizeResponse> {
        return memberSizeRepository.findAllMemberSizeResponseBy(memberId)
            .map { MemberSizeResponse.from(it) }

    }

}