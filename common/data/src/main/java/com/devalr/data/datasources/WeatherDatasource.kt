package com.devalr.data.datasources

import com.devalr.data.dto.DailyWeatherDto

interface WeatherDatasource {
    suspend fun fetchDailyWeather(cityCode: String): DailyWeatherDto?
}
