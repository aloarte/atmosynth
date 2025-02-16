package com.devalr.domain.repositories

import com.devalr.domain.model.weather.hourly.HourlyWeatherDataBo

interface WeatherRepository {
    suspend fun fetchDailyWeather(cityCode: String): HourlyWeatherDataBo?
    suspend fun fetchHourlyWeather(cityCode: String): HourlyWeatherDataBo?

}
