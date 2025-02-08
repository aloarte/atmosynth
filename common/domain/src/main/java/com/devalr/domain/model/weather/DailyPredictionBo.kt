package com.devalr.domain.model.weather

import com.devalr.domain.SunEventData

data class DailyPredictionBo(
    val date: String,
    val hourlyData: List<HourlyWeatherBo>,
    val sunEvents: List<SunEventData>
)
