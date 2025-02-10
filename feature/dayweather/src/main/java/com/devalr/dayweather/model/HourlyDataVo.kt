package com.devalr.dayweather.model

import java.time.LocalDateTime

abstract class HourlyDataVo {
    abstract val hour: String
    abstract val completeTime: LocalDateTime
}
