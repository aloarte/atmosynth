package com.devalr.dayweather.model.now

import com.devalr.dayweather.model.enums.SkyStateIcon

data class NowWeatherDataVo(
    val temperature: WeatherMaxMin,
    val thermalSensation: WeatherMaxMin,
    val humidity: WeatherMaxMin,
    val skyState: SkyStateIcon
)
