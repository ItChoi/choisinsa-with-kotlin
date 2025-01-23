package com.mall.choisinsa.common.infrastructure

import com.mall.choisinsa.common.port.ClockHolder
import com.mall.choisinsa.common.port.UuidHolder
import org.springframework.stereotype.Component
import java.time.Clock
import java.util.UUID

@Component
class SystemClockHolder(

) : ClockHolder {

    override fun millis(): Long {
        return Clock.systemUTC().millis()
    }
}