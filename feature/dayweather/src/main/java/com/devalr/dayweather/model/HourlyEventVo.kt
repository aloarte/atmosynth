package com.devalr.dayweather.model

import com.devalr.dayweather.model.enums.HourlyEvent

class HourlyEventVo(
    override val hour: String,
    val event: HourlyEvent
) : HourlyDataVo()
