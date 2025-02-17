package com.devalr.dayweather.model.hourly

import java.time.LocalDateTime

abstract class HourlyDataVo {
    abstract val hour: String
    abstract val completeTime: LocalDateTime
}
