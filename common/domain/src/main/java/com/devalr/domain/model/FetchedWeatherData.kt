package com.devalr.domain.model

import com.devalr.domain.model.weather.daily.DailyWeatherDataBo
import com.devalr.domain.model.weather.hourly.HourlyWeatherDataBo

data class FetchedWeatherData(val hourlyData: HourlyWeatherDataBo, val dailyData: DailyWeatherDataBo)