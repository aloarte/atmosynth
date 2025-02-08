package com.devalr.domain

import com.devalr.domain.model.SunEvent

data class HourlyEventData(
    val event: SunEvent,
    val time: String
)
