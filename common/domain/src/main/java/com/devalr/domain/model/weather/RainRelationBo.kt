package com.devalr.domain.model.weather

import com.devalr.domain.model.WeatherTime

data class RainRelationBo(
    val rainProbability: Float,
    val time: WeatherTime
)
