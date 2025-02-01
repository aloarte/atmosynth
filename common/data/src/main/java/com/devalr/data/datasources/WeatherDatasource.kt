package com.devalr.data.datasources

interface WeatherDatasource {
    suspend fun fetchDailyWeather(cityCode: String): List<String>
}
