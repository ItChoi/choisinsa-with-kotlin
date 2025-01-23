package com.mall.choisinsa.common.infrastructure

import com.mall.choisinsa.common.port.ClockHolder
import com.mall.choisinsa.common.port.UuidHolder
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class SystemUuidHolder(

) : UuidHolder {

    override fun random(): String {
        return UUID.randomUUID().toString()
    }
}