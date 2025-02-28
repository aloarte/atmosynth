package com.devalr.dayweather.interactions

import com.devalr.dayweather.model.hourly.HourlyDataVo
import com.devalr.dayweather.model.now.NowWeatherDataVo

data class State(
    val loadingStates: BooleanLoadingStates = BooleanLoadingStates(),
    val promptResults: PromptResults = PromptResults(),
    val weatherByHours: List<HourlyDataVo> = emptyList(),
    val dailyWeather: NowWeatherDataVo? = null
)
