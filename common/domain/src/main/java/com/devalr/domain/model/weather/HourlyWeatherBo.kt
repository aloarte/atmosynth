package com.devalr.domain.model.weather

import com.devalr.domain.model.enums.SkyState
import com.devalr.domain.model.enums.WeatherTime
import java.time.LocalDateTime

data class HourlyWeatherBo(
    val humidity: Int,
    val rainProbability: Float,
    val skyState: SkyState,
    val snowProbability: Float,
    val temperature: Int,
    val thermalSensation: Int,
    val time: WeatherTime,
    val completeTime: LocalDateTime
)
