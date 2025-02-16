package com.devalr.domain.model.weather.daily

import java.time.LocalDateTime

data class DailyWeatherBo(
    val rainProbabilities: List<PrecipitationProbability>,
    val snowProbabilities: List<PrecipitationProbability>,
    val skyStates : List<DailySkyState>,
    val temperatures: MaxMinValueBo,
    val thermalSensation: MaxMinValueBo,
    val humidity: MaxMinValueBo,
    val uvMax: Int,
    val date: LocalDateTime
)
