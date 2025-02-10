package com.devalr.dayweather.model

import com.devalr.dayweather.model.enums.HourlyEvent
import java.time.LocalDateTime

class HourlyEventVo(
    override val hour: String,
    override val completeTime: LocalDateTime,
    val event: HourlyEvent
) : HourlyDataVo()
