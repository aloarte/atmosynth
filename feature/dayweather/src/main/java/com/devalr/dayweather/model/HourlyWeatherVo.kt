package com.devalr.dayweather.model

data class HourlyWeatherVo(
    override val hour: String,
    val humidity: String,
    val rainProbability: String,
    val snowProbability: String,
    val temperature: String,
    val thermalSensation: String
) : HourlyDataVo()
