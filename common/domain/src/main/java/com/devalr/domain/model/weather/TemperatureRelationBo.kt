package com.devalr.domain.model.weather

import com.devalr.domain.model.WeatherTime
import java.time.LocalDateTime

data class TemperatureRelationBo(
    val temperature: Int,
    val time: WeatherTime,
    val completeTime: LocalDateTime
)
