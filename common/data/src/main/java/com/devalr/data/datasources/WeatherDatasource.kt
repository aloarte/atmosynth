package com.devalr.data.datasources

import com.devalr.data.dto.WeatherDataDaily

interface WeatherDatasource {
    suspend fun fetchDailyWeather(cityCode: String): WeatherDataDaily?
}
