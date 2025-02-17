package com.devalr.dayweather.model.hourly

import com.devalr.dayweather.model.enums.HourlyEvent
import java.time.LocalDateTime

class HourlyEventVo(
    override val hour: String,
    override val completeTime: LocalDateTime,
    val event: HourlyEvent
) : HourlyDataVo()
