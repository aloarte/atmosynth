package com.devalr.domain.model.weather

import com.devalr.domain.model.WeatherTime

data class TemperatureRelationBo(
    val temperature: Int,
    val time: WeatherTime,
)
