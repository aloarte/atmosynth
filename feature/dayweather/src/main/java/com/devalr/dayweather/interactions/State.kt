package com.devalr.dayweather.interactions

import com.devalr.dayweather.model.HourlyDataVo
import com.devalr.dayweather.model.MainWeatherDataVo

data class State(
    val promptResult: String = "",
    val mainWeatherData: MainWeatherDataVo? = null,
    val weatherByHours: List<HourlyDataVo> = emptyList(),
)
