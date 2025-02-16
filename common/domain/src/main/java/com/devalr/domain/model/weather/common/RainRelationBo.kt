package com.devalr.domain.model.weather.common

import com.devalr.domain.model.enums.WeatherTime

data class RainRelationBo(
    val rainProbability: Float,
    val time: WeatherTime
)
