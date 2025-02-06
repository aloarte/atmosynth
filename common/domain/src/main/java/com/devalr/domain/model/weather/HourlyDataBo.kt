package com.devalr.domain.model.weather

import com.devalr.domain.model.SkyState
import com.devalr.domain.model.WeatherTime

data class HourlyDataBo(
    val humidity: Int,
    val rainProbability: Float,
    val skyState: SkyState,
    val snowProbability: Float,
    val temperature: Int,
    val thermalSensation: Int,
    val time: WeatherTime,
)
