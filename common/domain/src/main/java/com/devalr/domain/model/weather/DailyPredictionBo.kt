package com.devalr.domain.model.weather

import com.devalr.domain.HourlyEventData

data class DailyPredictionBo(
    val date: String,
    val hourlyData: List<HourlyWeatherBo>,
    val sunEvents: List<HourlyEventData>
)
