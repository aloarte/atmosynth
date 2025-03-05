package com.devalr.dayweather.interactions

import com.devalr.dayweather.model.hourly.HourlyDataVo
import com.devalr.dayweather.model.now.NowWeatherDataVo
import com.devalr.domain.model.CityBo

data class State(
    val loadingStates: BooleanLoadingStates = BooleanLoadingStates(),
    val promptResults: PromptResults = PromptResults(),
    val weatherByHours: List<HourlyDataVo> = emptyList(),
    val activeCity: CityBo? = null,
    val dailyWeather: NowWeatherDataVo? = null
)
