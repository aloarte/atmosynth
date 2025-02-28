package com.devalr.data.datasources

import com.devalr.data.dto.dailyweather.daily.DailyWeatherDto
import com.devalr.data.dto.dailyweather.hourly.HourlyWeatherDto

interface WeatherDatasource {
    suspend fun fetchDailyWeather(cityCode: String): DailyWeatherDto?
    suspend fun fetchHourlyWeather(cityCode: String): HourlyWeatherDto?
}
