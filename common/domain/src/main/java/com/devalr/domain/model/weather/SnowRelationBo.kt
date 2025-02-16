package com.devalr.domain.model.weather

import com.devalr.domain.model.enums.WeatherTime

data class SnowRelationBo(
    val snowProbability: Float,
    val time: WeatherTime
)
