package com.devalr.data.datasources

import com.devalr.data.dto.dailyweather.DailyWeatherDto

interface WeatherDatasource {
    suspend fun fetchDailyWeather(cityCode: String): DailyWeatherDto?
}
