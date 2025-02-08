package com.devalr.dayweather.model

import com.devalr.dayweather.model.enums.HourlyEvent

class HourlyEventVo(
    val event: HourlyEvent,
    val time: String
) : HourlyDataVo()
