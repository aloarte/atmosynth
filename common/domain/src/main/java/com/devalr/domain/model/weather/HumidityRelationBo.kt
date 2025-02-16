package com.devalr.domain.model.weather

import com.devalr.domain.model.enums.WeatherTime

data class HumidityRelationBo(
    val humidity: Int,
    val time: WeatherTime
)
