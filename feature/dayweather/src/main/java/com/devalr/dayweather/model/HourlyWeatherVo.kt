package com.devalr.dayweather.model

data class HourlyWeatherVo(
    val date: String,
    val hour: String,
    val temperature: String,
    val humidity: String,
    val thermalSensation: String,
    val snowProbability: String,
    val rainProbability: String,
) : HourlyDataVo()
