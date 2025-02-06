package com.devalr.domain.model.weather

import com.devalr.domain.model.WeatherTime

data class HumidityRelationBo(
    val humidity: Int,
    val time: WeatherTime,
)
