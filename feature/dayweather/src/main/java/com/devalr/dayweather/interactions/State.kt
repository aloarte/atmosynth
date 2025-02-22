package com.devalr.dayweather.interactions

import com.devalr.dayweather.model.PromptStateVo
import com.devalr.dayweather.model.hourly.HourlyDataVo
import com.devalr.dayweather.model.now.NowWeatherDataVo

data class State(
    val displayHumidityDetail: Boolean = false,
    val loadingWeather: Boolean = false,
    val errorReceived: Boolean = false,
    val promptSummary: PromptStateVo = PromptStateVo(),
    val promptHumidity: PromptStateVo = PromptStateVo(),
    val weatherByHours: List<HourlyDataVo> = emptyList(),
    val dailyWeather: NowWeatherDataVo? = null
)
