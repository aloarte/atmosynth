package com.devalr.domain.model.weather.daily


data class DailyPredictionBo(
    val date: String,
    val dailyData: List<DailyWeatherBo>,
)
