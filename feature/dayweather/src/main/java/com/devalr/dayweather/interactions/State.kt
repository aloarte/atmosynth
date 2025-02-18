package com.devalr.dayweather.interactions

import com.devalr.dayweather.model.hourly.HourlyDataVo
import com.devalr.dayweather.model.now.NowWeatherDataVo

data class State(
    val loadingWeather: Boolean = false,
    val loadingAiPrompt: Boolean = false,
    val errorReceived: Boolean = false,
    val promptResult: String = "",
    val weatherByHours: List<HourlyDataVo> = emptyList(),
    val dailyWeather: NowWeatherDataVo? = null
)
