package com.devalr.domain

import com.devalr.domain.model.enums.SunEvent
import java.time.LocalDateTime

data class HourlyEventData(
    val event: SunEvent,
    val time: LocalDateTime
)
