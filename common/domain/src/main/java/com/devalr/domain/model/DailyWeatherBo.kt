package com.devalr.domain.model

data class DailyWeatherBo(
    val predictions: List<DailyPrediction>,
) {
    data class DailyPrediction(
        val date: String,
        val temperatures: List<TemperatureRelation>,
    ) {
        class TemperatureRelation(
            val temperature: Int,
            val time: WeatherTime,
        )
    }
}
