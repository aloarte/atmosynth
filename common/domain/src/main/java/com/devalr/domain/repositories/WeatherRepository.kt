package com.devalr.domain.repositories

import com.devalr.domain.model.weather.DailyWeatherBo

interface WeatherRepository {
    suspend fun fetchDailyWeather(cityCode: String): DailyWeatherBo?
}
