package com.devalr.domain.model.weather.common

import com.devalr.domain.model.enums.WeatherTime
import java.time.LocalDateTime

data class TemperatureRelationBo(
    val temperature: Int,
    val time: WeatherTime,
    val completeTime: LocalDateTime
)
