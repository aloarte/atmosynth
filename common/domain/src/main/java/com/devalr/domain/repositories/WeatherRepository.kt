package com.devalr.domain.repositories

interface WeatherRepository {
    suspend fun fetchDailyWeather(cityCode: String): List<String>
}
