package com.devalr.domain.model.weather

import com.devalr.domain.HourlyEventData

data class HourlyPredictionBo(
    val date: String,
    val hourlyData: List<HourlyWeatherBo>,
    val sunEvents: List<HourlyEventData>
)
