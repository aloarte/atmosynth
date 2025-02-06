package com.devalr.domain.model.weather

data class DailyPredictionBo(
    val date: String,
    val hourlyData: List<HourlyDataBo>,
)
